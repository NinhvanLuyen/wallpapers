package luyen.ninh.wallpaperx.domain.usecase

import androidx.lifecycle.LiveData
import luyen.ninh.wallpaperx.domain.data.Result
import luyen.ninh.wallpaperx.domain.data.local.room.entities.StepEntity

/**
 * Created by luyen_ninh on 2019-10-26.
 */
interface StepUC {
    suspend fun getAllStepPerDay(startTime:Long,endTime:Long):Result<Int>
    fun getAllStepPerDayLive(startTime:Long,endTime:Long):LiveData<List<StepEntity>>
    suspend fun insertStep(step:StepEntity):Result<Int>
}