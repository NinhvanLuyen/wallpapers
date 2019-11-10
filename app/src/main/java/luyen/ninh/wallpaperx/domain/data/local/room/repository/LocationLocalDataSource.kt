package luyen.ninh.wallpaperx.domain.data.local.room.repository

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import luyen.ninh.wallpaperx.domain.data.Result
import luyen.ninh.wallpaperx.domain.data.local.room.dao.LocationDao
import luyen.ninh.wallpaperx.domain.data.local.room.entities.LocationEntity
import luyen.ninh.wallpaperx.helpers.LocationHelper
import java.lang.NullPointerException

/**
 * Created by luyen_ninh on 2019-10-12.
 */
class LocationLocalDataSource internal constructor(private val locationDao: LocationDao,
                                                   private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {


    suspend fun insert(newLocation: LocationEntity):Result<Int>{
        return withContext(ioDispatcher) {
            return@withContext try {
                locationDao.getLastLocation().let { lastLocation ->
                    val last = LatLng(lastLocation.lat,lastLocation.lng)
                    val cur = LatLng(newLocation.lat, newLocation.lng)
                    newLocation.distant = LocationHelper.getDistance(last,cur)
                    //mili seconds moving between two step
                    val toSeconds = (System.currentTimeMillis() - lastLocation.time)/1000
            //                    Log.e("WTF","seconds : $toSeconds")
                    val toMinus = if((toSeconds/60F)==0F) 1F else toSeconds/60F  // avoid infinity case
                    val toHours = if((toMinus/ 60F) == 0F) 1F else toMinus / 60F // avoid infinity case

                    val s  = (newLocation.distant / 1000)

                    newLocation.velocityKmH = s / toHours
                    newLocation.velocityMmM = (newLocation.distant) / toMinus
                }
                locationDao.insert(newLocation)
                val size  = locationDao.getAllLocation().size
                if (size > 0)
                    Result.Success(size)
                else
                    Result.Error(NullPointerException())

            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    suspend fun deleteAll() = locationDao.deleteAll()

    suspend fun deleteID(idLocation: Int) = locationDao.deleteLocationAt(idLocation);

    suspend fun getAll(): Result<List<LocationEntity>> {

        return withContext(ioDispatcher) {

            return@withContext try {
                Result.Success(locationDao.getAllLocation());
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    suspend fun getLocationDetail(idLocation: Int):Result<LocationEntity> {
    return try{
        Result.Success(locationDao.getLocationAt(idLocation))
    }catch (e:Exception){
        Result.Error(e)
    }
    }

    fun getAllLive() = locationDao.getLiveAllLocation()
    fun getCurrentVelocityKmPH() = locationDao.getCurrentVelocityKmPH()
    fun getCurrentVelocityMPM() = locationDao.getCurrentVelocityMPM()
    fun getVelocityKmPHAvg(startTime:Long, endTime:Long) = locationDao.getVelocityKmPHAvg(startTime,endTime)
}