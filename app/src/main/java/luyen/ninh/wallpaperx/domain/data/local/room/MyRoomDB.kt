package luyen.ninh.wallpaperx.domain.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import luyen.ninh.wallpaperx.domain.data.local.room.dao.LocationDao
import luyen.ninh.wallpaperx.domain.data.local.room.dao.StepDao
import luyen.ninh.wallpaperx.domain.data.local.room.entities.LocationEntity
import luyen.ninh.wallpaperx.domain.data.local.room.entities.StepEntity

/**
 * Created by luyen_ninh on 2019-10-12.
 */
@Database(entities = [(LocationEntity::class),(StepEntity::class)], version = 1, exportSchema = false)
abstract class MyRoomDB : RoomDatabase() {
    abstract fun getLocationDao():LocationDao
    abstract fun getStepDao(): StepDao

    companion object {
        fun getInstance(context: Context): MyRoomDB {
                synchronized(MyRoomDB::class) {
                    return Room.databaseBuilder(context.applicationContext,
                        MyRoomDB::class.java, "wallpaper_test.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }
}