package luyen.ninh.wallpaperx.domain.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import luyen.ninh.wallpaperx.domain.data.local.room.entities.LocationEntity

/**
 * Created by luyen_ninh on 2019-10-12.
 */
@Dao
interface LocationDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location:LocationEntity)

    @Query("SELECT * FROM location order by time DESC limit 1")
    suspend fun getLastLocation():LocationEntity

    @Query("SELECT * FROM location order by time DESC")
    suspend fun getAllLocation():List<LocationEntity>

    @Query("SELECT * FROM location order by time DESC ")
    fun getLiveAllLocation():LiveData<List<LocationEntity>>

    @Query("DELETE FROM location")
    suspend fun deleteAll()

    @Query("DELETE FROM LOCATION WHERE id =:id")
    suspend fun deleteLocationAt(id:Int)

    @Query("SELECT * FROM location WHERE id=:id")
    suspend fun getLocationAt(id: Int): LocationEntity

}