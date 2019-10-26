package luyen.ninh.wallpaperx.domain.usecase

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import luyen.ninh.wallpaperx.domain.data.Result
import luyen.ninh.wallpaperx.domain.data.local.room.entities.LocationEntity

/**
 * Created by luyen_ninh on 2019-10-13.
 */
interface LocationUC {

    suspend fun getLocationDetail(locationID: Int): Result<LocationEntity>
    suspend fun getAllLocation(): Result<List<LocationEntity>>
    suspend fun deleteLocationAt(locationID: Int)
    suspend fun deleteAllLocation()
    suspend fun saveLocation(location:LocationEntity): Result<Int>
    fun getAllLiveLocation(): LiveData<List<LocationEntity>>
    suspend fun fetchLocation(): Result<Location?>
}