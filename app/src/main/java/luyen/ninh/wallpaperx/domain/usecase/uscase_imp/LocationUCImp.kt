package luyen.ninh.wallpaperx.domain.usecase.uscase_imp

import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import luyen.ninh.wallpaperx.domain.data.Result
import luyen.ninh.wallpaperx.domain.data.local.room.entities.LocationEntity
import luyen.ninh.wallpaperx.domain.data.local.room.repository.LocationLocalDataSource
import luyen.ninh.wallpaperx.domain.data.remote.LocationRemoteDataSource
import luyen.ninh.wallpaperx.domain.usecase.LocationUC
import java.lang.Exception

/**
 * Created by luyen_ninh on 2019-10-13.
 */
class LocationUCImp(
    private val local: LocationLocalDataSource,
    private val remote: LocationRemoteDataSource,
    private val GPSUseCase: GPSUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LocationUC {

    override suspend fun getLocationDetail(locationID: Int): Result<LocationEntity> {
        return local.getLocationDetail(locationID)
    }

    override suspend fun getAllLocation(): Result<List<LocationEntity>> {
        return local.getAll()
    }
    override fun getAllLiveLocation(): LiveData<List<LocationEntity>> {
        return local.getAllLive()
    }

    override suspend fun deleteLocationAt(locationID: Int) {
        return local.deleteID(locationID)
    }

    override suspend fun deleteAllLocation() {
        return local.deleteAll()
    }

    override suspend fun saveLocation(location: LocationEntity):Result<Int> {
        return local.insert(location)
    }
    override suspend fun fetchLocation():Result<Location?>{
        Log.e("WTF","fetchLocation_UC")
        return withContext(ioDispatcher){
            return@withContext try {

                Result.Success(GPSUseCase.getLocation())
            }catch (e:Exception){
                    Result.Error(e)
            }

        }
    }
}