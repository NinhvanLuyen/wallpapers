package luyen.ninh.wallpaperx.presentations.features.home

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Service
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import java.util.*


/**
 * Created by luyen_ninh on 2019-10-19.
 */
class LocationRequestGoogleHelper :  LocationListener{

    private lateinit var locationCallback: LocationCallback
    fun onChangeLocation(locationCallback: LocationCallback):LocationRequestGoogleHelper{
        this.locationCallback = locationCallback
        return this
    }
    override fun onLocationChanged(location: Location?) {
        locationCallback.onChangeLocation(location)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }

    private lateinit var mLocationManager:LocationManager


    @SuppressLint("MissingPermission")
    fun requestTrackLocation() {

        val criteria = Criteria()
        criteria.accuracy = Criteria.ACCURACY_FINE
        criteria.powerRequirement = Criteria.POWER_HIGH
        criteria.isAltitudeRequired = false
        criteria.isSpeedRequired = false
        criteria.isCostAllowed = true
        criteria.isBearingRequired = false

        //API level 9 and up
        criteria.horizontalAccuracy = Criteria.ACCURACY_HIGH
        criteria.verticalAccuracy = Criteria.ACCURACY_HIGH
        mLocationManager.requestLocationUpdates(1*1000, 1F, criteria, this, null)

        // Fire receiving location
        //LocationAction.onChanging();
    }
    fun build(context: Context):LocationRequestGoogleHelper {
        mLocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val mGoogleApiClient = GoogleApiClient.Builder(context)
            .addApi(LocationServices.API)
            .build()
        mGoogleApiClient.connect()
        return this
    }
}
interface LocationCallback{
    fun onChangeLocation(location:Location?)
}