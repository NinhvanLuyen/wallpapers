package luyen.ninh.wallpaperx.domain.usecase.uscase_imp

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.util.Log

/**
 * Created by luyen_ninh on 2019-10-18.
 **/
class GPSUseCase(context: Context) {

    private val tag = GPSUseCase::class.java.simpleName
    private var mLocationManager = context
        .getSystemService(Context.LOCATION_SERVICE) as LocationManager

    fun getLocation(): Location? {
        Log.e("WTF","getLocation")

        val gpsLocation = getLocationByProvider(LocationManager.GPS_PROVIDER)
        val networkLocation = getLocationByProvider(LocationManager.NETWORK_PROVIDER)
        // if we have only one location available, the choice is easy
        if (gpsLocation == null) {
            Log.d(tag, "No GPS Location available.")
            return networkLocation
        }
        if (networkLocation == null) {
            Log.d(tag, "No Network Location available")
            return gpsLocation
        }
        // a locationupdate is considered 'old' if its older than the configured
        // update interval. this means, we didn'viewChangeistener get a
        // update from this provider since the last check
        val old = System.currentTimeMillis()// - getGPSCheckMilliSecsFromPrefs();
        val gpsIsOld = gpsLocation.time < old
        val networkIsOld = networkLocation.time < old
        // gps is current and available, gps is better than network
        if (!gpsIsOld) {
            Log.d(tag, "Returning current GPS Location")
            return gpsLocation
        }
        // gps is old, we can'viewChangeistener trust it. use network location
        if (!networkIsOld) {
            Log.d(tag, "GPS is old, Network is current, returning network")
            return networkLocation
        }
        // both are old return the newer of those two
        return if (gpsLocation.time > networkLocation.time) {
            Log.d(tag, "Both are old, returning gps(newer)")
            gpsLocation
        } else {
            Log.d(tag, "Both are old, returning network(newer)")
            networkLocation
        }
    }

    /**
     * get the last known location from a specific provider (network/gps)
     */
    // Always check permission before using
    @SuppressLint("MissingPermission")
    private fun getLocationByProvider(provider: String): Location? {
        if (!isProviderSupported(provider)) {
            return null
        }

        val manager = mLocationManager
        try {
            if (manager.isProviderEnabled(provider)) {
                return manager.getLastKnownLocation(provider)
            }
        } catch (e: IllegalArgumentException) {
            Log.d(tag, "Cannot acces Provider $provider")
        }

        return null
    }

    private fun isProviderSupported(provider: String): Boolean {
        return try {
            mLocationManager.isProviderEnabled(provider)
        } catch (ex: Exception) {
            false
        }

    }
}