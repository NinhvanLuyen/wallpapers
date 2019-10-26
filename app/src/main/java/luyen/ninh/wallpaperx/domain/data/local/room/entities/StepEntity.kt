package luyen.ninh.wallpaperx.domain.data.local.room.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by luyen_ninh on 2019-10-26.
 */
@Parcelize
@Entity(tableName = "step")
data class StepEntity(
    @PrimaryKey
    val id:Long
) : Parcelable{
    constructor():this(System.currentTimeMillis())
}