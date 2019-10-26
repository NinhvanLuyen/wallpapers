package luyen.ninh.wallpaperx.domain.data.local.room.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import luyen.ninh.wallpaperx.domain.data.Result
import luyen.ninh.wallpaperx.domain.data.local.room.dao.StepDao
import luyen.ninh.wallpaperx.domain.data.local.room.entities.StepEntity

/**
 * Created by luyen_ninh on 2019-10-26.
 */
class StepLocalDataSource internal constructor(
    private val stepDao: StepDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun saveStep(step: StepEntity): Result<Int> {
        return withContext(ioDispatcher) {
            return@withContext try {
                stepDao.insert(step)
                val size = stepDao.getStepPerDay().size
                if (size > 0)
                    Result.Success(size)
                else
                    Result.Error(NullPointerException())

            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    suspend fun getStepPerDay(startTime: Long, endTime: Long):Result<Int> {
        return withContext(ioDispatcher) {
            return@withContext try {
                val size = stepDao.getStepPerDay().size
                if (size > 0)
                    Result.Success(size)
                else
                    Result.Error(NullPointerException())
            } catch (e: Exception) {
                Result.Error(NullPointerException())
            }
        }
    }

    fun getAllStepLive(startTime: Long, endTime: Long) = stepDao.getStepPerDayLive(startTime,endTime)

}