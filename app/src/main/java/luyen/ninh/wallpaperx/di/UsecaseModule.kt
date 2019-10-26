package luyen.ninh.wallpaperx.di

import android.content.Context
import luyen.ninh.wallpaperx.MyApplication
import luyen.ninh.wallpaperx.domain.data.local.room.repository.LocationLocalDataSource
import luyen.ninh.wallpaperx.domain.data.local.room.repository.StepLocalDataSource
import luyen.ninh.wallpaperx.domain.data.remote.LocationRemoteDataSource
import luyen.ninh.wallpaperx.domain.data.remote.StepRemoteDataSource
import luyen.ninh.wallpaperx.domain.usecase.*
import luyen.ninh.wallpaperx.domain.usecase.uscase_imp.GPSUseCase
import luyen.ninh.wallpaperx.domain.usecase.uscase_imp.LocationUCImp
import luyen.ninh.wallpaperx.domain.usecase.UseCase
import luyen.ninh.wallpaperx.domain.usecase.uscase_imp.StepUcImp
import org.koin.dsl.module.module

/**
 * Created by luyen_ninh on 2019-10-13.
 */
val useCaseModule = module {


    ///Define another use case
    single { createLocationUseCase(get(), get(),get()) }
    single { createStepUseCase(get(),get()) }



    //add feature UseCase
    single { createUseCase(get(),get()) }
    single { createLocationHelper(MyApplication.app) }

}

fun createStepUseCase(stepLocalDataSource: StepLocalDataSource,stepRemoteDataSource: StepRemoteDataSource):StepUC {
    return StepUcImp(stepLocalDataSource, stepRemoteDataSource)
}

fun createLocationHelper(context: Context): GPSUseCase {
    return GPSUseCase(context)
}


//Define feature use case
private fun createLocationUseCase(locationLocalDataSource: LocationLocalDataSource, locationRemoteDataSource: LocationRemoteDataSource, GPSUseCase: GPSUseCase):LocationUC =
    LocationUCImp(
        locationLocalDataSource,
        locationRemoteDataSource,
        GPSUseCase
    )

private fun createUseCase(locationUC: LocationUC, stepUC:StepUC) = UseCase(locationUC, stepUC)