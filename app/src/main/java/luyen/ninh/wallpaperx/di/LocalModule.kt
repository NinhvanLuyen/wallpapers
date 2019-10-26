package luyen.ninh.wallpaperx.di

import android.content.Context
import luyen.ninh.wallpaperx.MyApplication
import luyen.ninh.wallpaperx.domain.data.local.room.MyRoomDB
import luyen.ninh.wallpaperx.domain.data.local.room.dao.LocationDao
import luyen.ninh.wallpaperx.domain.data.local.room.dao.StepDao
import luyen.ninh.wallpaperx.domain.data.local.room.repository.LocationLocalDataSource
import luyen.ninh.wallpaperx.domain.data.local.room.repository.StepLocalDataSource
import org.koin.dsl.module.module

/**
 * Created by luyen_ninh on 2019-10-13.
 */
val localModule = module {

    //Define RoomDB
    single { createRoomDB(MyApplication.app) }

    //define dao
    single { createDaoLocation(get()) }
    single { createDaoStep(get()) }

    //define local data source
    single { createLocalRepo(get()) }
    single { createLocalStepRepo(get()) }
    //xxxx LocationLocalDataSource
}

private fun createRoomDB(context: Context): MyRoomDB = MyRoomDB.getInstance(context)

//location
private fun createDaoLocation(myRoomDB: MyRoomDB): LocationDao = myRoomDB.getLocationDao()

private fun createLocalRepo(dao: LocationDao): LocationLocalDataSource = LocationLocalDataSource(dao)

//step
private fun createDaoStep(myRoomDB: MyRoomDB):StepDao = myRoomDB.getStepDao()

private fun createLocalStepRepo(dao: StepDao):StepLocalDataSource = StepLocalDataSource(dao)