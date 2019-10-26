package luyen.ninh.wallpaperx.presentations.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import luyen.ninh.wallpaperx.domain.data.local.room.entities.LocationEntity
import luyen.ninh.wallpaperx.domain.usecase.UseCase
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*


/**
 * Created by luyen_ninh on 2019-10-17.
 */
class LocationWorker(val context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams),
    KoinComponent {
    private val useCase: UseCase by inject()

    override suspend fun doWork(): Result {
        useCase.locationUC.fetchLocation()
            .let { res ->
                when (res) {
                    is luyen.ninh.wallpaperx.domain.data.Result.Success -> res.data?.let {
                        saveLocation(it.latitude, it.longitude)
                    }
                    is luyen.ninh.wallpaperx.domain.data.Result.Error -> res.exception.printStackTrace()
                    else -> Log.e("WTF","Error")
                }
            }
        return Result.success()
    }

    private suspend fun saveLocation(lat: Double, long: Double) {
        useCase.locationUC.saveLocation(
            LocationEntity(
                UUID.randomUUID().toString(),
                System.currentTimeMillis(),
                "Worker",
                0F, lat, long
            )
        )
            .let {
                when (it) {
                    is luyen.ninh.wallpaperx.domain.data.Result.Success -> {
                        Log.e("WTF", "insert success ${it.data}")
                    }
                    is luyen.ninh.wallpaperx.domain.data.Result.Error -> {
                        Log.e("WTF", "insert fail")
                        it.exception.printStackTrace()

                    }
                    is luyen.ninh.wallpaperx.domain.data.Result.Loading -> {
                        Log.e("WTF", "insert loading ")

                    }
                }

            }
    }
}