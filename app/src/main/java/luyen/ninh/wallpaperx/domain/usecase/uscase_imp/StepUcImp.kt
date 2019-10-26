package luyen.ninh.wallpaperx.domain.usecase.uscase_imp

import androidx.lifecycle.LiveData
import luyen.ninh.wallpaperx.domain.data.Result
import luyen.ninh.wallpaperx.domain.data.local.room.entities.StepEntity
import luyen.ninh.wallpaperx.domain.data.local.room.repository.StepLocalDataSource
import luyen.ninh.wallpaperx.domain.data.remote.StepRemoteDataSource
import luyen.ninh.wallpaperx.domain.usecase.StepUC

/**
 * Created by luyen_ninh on 2019-10-26.
 */
class StepUcImp(private val local: StepLocalDataSource,
                private val remote: StepRemoteDataSource) :StepUC {

    override suspend fun insertStep(step: StepEntity): Result<Int> {
        return local.saveStep(step)
    }

    override suspend fun getAllStepPerDay(startTime: Long, endTime: Long): Result<Int> {
        return local.getStepPerDay(startTime, endTime)
    }

    override fun getAllStepPerDayLive(startTime: Long, endTime: Long): LiveData<List<StepEntity>> {
        return local.getAllStepLive(startTime, endTime)
    }

}