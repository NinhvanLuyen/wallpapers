package luyen.ninh.wallpaperx.presentations.features.home

import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import luyen.ninh.wallpaperx.R
import luyen.ninh.wallpaperx.domain.data.Result
import luyen.ninh.wallpaperx.domain.data.local.room.entities.LocationEntity
import luyen.ninh.wallpaperx.domain.usecase.UseCase
import luyen.ninh.wallpaperx.presentations.helpers.PermissionHelper
import luyen.ninh.wallpaperx.presentations.services.TrackingLocationService
import luyen.ninh.wallpaperx.presentations.services.TrackingStepService
import org.koin.android.ext.android.inject
import java.util.*

class MainActivity : AppCompatActivity() {
    val useCase: UseCase by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var navController: NavController = findNavController(R.id.navHostFragment)
        //WorkManger
//        val request = PeriodicWorkRequest
//            .Builder(LocationWorker::class.java, 5, TimeUnit.MINUTES)
//            .addTag("WTF")
//            .build()
        PermissionHelper.requestLocationPermission(this)
//        WorkManager.getInstance(this).enqueue(request)
//        WorkManager.getInstance(this).getWorkInfosByTagLiveData("WTF").observe(this, Observer { state ->
//            state?.let {
//                if (it == null || it.isEmpty()) {
//                    Log.e("WTF", "Empty")
//                } else {
//                    // We only care about the one output status.
//                    // Every continuation has only one worker tagged TAG_OUTPUT
//                    val workInfo = it.get(0)
//
//                    when (workInfo.state) {
//                        WorkInfo.State.ENQUEUED -> {
//                            Log.e("WTF", "ENQUEUED")
//                        }
//
//                        WorkInfo.State.RUNNING -> {
//                            Log.e("WTF", "RUNNING")
//                        }
//
//                        WorkInfo.State.SUCCEEDED -> {
//                            val successOutputData = workInfo.outputData
//
//                            Log.e("WTF", "SUCCEEDED:")
//                        }
//                        WorkInfo.State.BLOCKED -> {
//                            val successOutputData = workInfo.outputData
//
//                            Log.e("WTF", "Block:")
//                        }
//                        WorkInfo.State.CANCELLED -> {
//                            val successOutputData = workInfo.outputData
//
//                            Log.e("WTF", "Cancel")
//                        }
//                        WorkInfo.State.FAILED -> {
//                            val successOutputData = workInfo.outputData
//
//                            Log.e("WTF", "Fail:")
//                        }
//                    }
//                }
//            }
//        })
//
//        //Alarm
//        val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        val trackingIntent = Intent(this, TrackingLocationService::class.java)
//        val trackingPendingIntent =
//            PendingIntent.getService(this, 1000, trackingIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//        alarmManager.run {
//            setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().timeInMillis, (60 * 1000), trackingPendingIntent)
//        }
        //Google location helper --> google service
//        LocationRequestGoogleHelper()
//            .onChangeLocation(object: LocationCallback {
//                override fun onChangeLocation(location: Location?) {
//                    GlobalScope.async {
//                        location?.let {
//                            saveLocation(location.latitude,location.longitude)
//                        }
//                    }
//                }
//            })
//            .build(this)
//            .requestTrackLocation()
//        GlobalScope.async {
//            repeat(1000) {
//                Log.w("WTF", "repeat(1000) ")
        if(PermissionHelper.isLocationPermissionGranted(this)) {
            if (Build.VERSION.SDK_INT >= 26) {
                val trackingIntent = Intent(this, TrackingLocationService::class.java)
                startForegroundService(trackingIntent)
            } else
                LocationRequestGoogleHelper()
                    .build(this)
                    .onChangeLocation(object : LocationCallback {
                        override fun onChangeLocation(location: Location?) {
                            location?.let {
                                GlobalScope.async {
                                    saveLocation(location.latitude, location.longitude)

                                }
                            }
                        }

                    }).requestTrackLocation()
        }
//                delay(60 * 1000L)
//            }
    }

    private suspend fun saveLocation(lat: Double, long: Double) {
        useCase.locationUC.saveLocation(
            LocationEntity(
                UUID.randomUUID().toString(),
                System.currentTimeMillis(),
                "LocationServices",
                0F, lat, long
            )
        )
            .let {
                when (it) {
                    is Result.Success -> {
                        Log.e("WTF", "insert success ${it.data}")
                    }
                    is Result.Error -> {
                        Log.e("WTF", "insert fail")
                        it.exception.printStackTrace()

                    }
                    is Result.Loading -> {
                        Log.e("WTF", "insert loading ")

                    }
                }

            }
    }
}

