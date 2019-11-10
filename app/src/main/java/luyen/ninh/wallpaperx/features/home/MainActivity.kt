package luyen.ninh.wallpaperx.features.home

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import luyen.ninh.wallpaperx.R
import luyen.ninh.wallpaperx.domain.data.local.room.entities.LocationEntity
import luyen.ninh.wallpaperx.domain.usecase.UseCase
import luyen.ninh.wallpaperx.helpers.PermissionHelper
import luyen.ninh.wallpaperx.services.TrackingLocationService
import luyen.ninh.wallpaperx.services.TrackingStepService
import org.koin.android.ext.android.inject
import java.util.*

class MainActivity : AppCompatActivity() {
    val useCase: UseCase by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findNavController(R.id.navHostFragment)
        if (PermissionHelper.isLocationPermissionGranted(this))
            startTrackingLocation()
        else
            PermissionHelper.requestLocationPermission(this)
        startService(Intent(this,TrackingStepService::class.java))
    }

    private fun startTrackingLocation() {
        if (PermissionHelper.isLocationPermissionGranted(this)) {
            if (Build.VERSION.SDK_INT >= 26) {
                val trackingIntent = Intent(this, TrackingLocationService::class.java)
                startForegroundService(trackingIntent)
            } else
                LocationRequestGoogleHelper.getInstance()
                    .build(this)
                    .onChangeLocation(object : LocationCallback {
                        override fun onChangeLocation(location: Location?) {
                            location?.let {
                                GlobalScope.async {
                                    useCase.locationUC.saveLocation(
                                        LocationEntity(
                                            UUID.randomUUID().toString(),
                                            System.currentTimeMillis(),
                                            "LocationServices", location.latitude, location.longitude
                                        )
                                    )

                                }
                            }
                        }

                    }).requestTrackLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermissionHelper.REQUEST_LOCATION
            && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            startTrackingLocation()

    }
}

