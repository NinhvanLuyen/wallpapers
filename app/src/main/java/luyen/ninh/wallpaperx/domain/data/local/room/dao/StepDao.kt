package luyen.ninh.wallpaperx.domain.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import luyen.ninh.wallpaperx.domain.data.local.room.entities.StepEntity
import luyen.ninh.wallpaperx.base.utils.TimeHelper

/**
 * Created by luyen_ninh on 2019-10-26.
 */
@Dao
interface StepDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(step: StepEntity)

    @Query("SELECT * FROM step order by id DESC limit 1")
    suspend fun getLastLocation(): StepEntity

    @Query("SELECT * FROM step where id >= :startTime and id <=:endTime ")
    suspend fun getStepPerDay(startTime:Long = TimeHelper.getStartTimeOfToday(),
                              endTime:Long =TimeHelper.getEndTimeOfToDay()):List<StepEntity>

    @Query("SELECT * FROM step where id >= :startTime and id <=:endTime ")
    fun getStepPerDayLive(startTime:Long = TimeHelper.getStartTimeOfToday(),
                              endTime:Long =TimeHelper.getEndTimeOfToDay()):LiveData<List<StepEntity>>

    @Query("DELETE FROM location")
    suspend fun deleteAll()

}