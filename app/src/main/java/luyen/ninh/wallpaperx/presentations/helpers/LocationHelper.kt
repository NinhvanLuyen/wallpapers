package luyen.ninh.wallpaperx.presentations.helpers

import android.location.Location
import com.google.android.gms.maps.model.LatLng

/**
 * Created by luyen_ninh on 2019-10-18.
 */
object LocationHelper {
    fun getDistance(source: LatLng?, destination: LatLng?): Float {
        if (source == null || destination == null) {
            return -1f
        }

        val result = FloatArray(3)

        Location.distanceBetween(
            source!!.latitude, source!!.longitude,
            destination!!.latitude, destination!!.longitude, result
        )

        return result[0]
    }
}