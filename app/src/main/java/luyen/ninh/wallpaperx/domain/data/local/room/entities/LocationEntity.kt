package luyen.ninh.wallpaperx.domain.data.local.room.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import luyen.ninh.wallpaperx.presentations.base.utils.TimeHelper
import java.text.DecimalFormat
import java.util.*

/**
 * Created by luyen_ninh on 2019-10-12.
 */
@Parcelize
@Entity(tableName = "location")
data class LocationEntity(
    @PrimaryKey
    var id: String,
    var time: Long,
    var from:String,
    var distant:Float,
    var lat: Double,
    var lng: Double
) : Parcelable{
    constructor():this(UUID.randomUUID().toString(),0L,"default",0F,0.0,0.0)

    fun toStringLatLon():String {
        val emo:String =  if(distant < 5) "\uD83C\uDFC3\u200D♂️" else "\uD83C\uDFCD"
        return "$emo  $lat - $lng"
    }
    fun getTimeToString():String = "⏱ "+TimeHelper.toString(time)
    fun getDistantMetters():String {
        val df = DecimalFormat()
        df.maximumFractionDigits = 2
    return "${df.format(distant)} m ↔️ (${df.format(distant/1000)} km)"
    }
}