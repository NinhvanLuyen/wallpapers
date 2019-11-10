package luyen.ninh.wallpaperx.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import luyen.ninh.wallpaperx.R
import luyen.ninh.wallpaperx.domain.data.Result
import luyen.ninh.wallpaperx.domain.data.local.room.entities.LocationEntity
import luyen.ninh.wallpaperx.domain.usecase.UseCase
import luyen.ninh.wallpaperx.features.home.LocationCallback
import luyen.ninh.wallpaperx.features.home.LocationRequestGoogleHelper
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import java.util.*


/**
 * Created by luyen_ninh on 2019-10-18.
 */
class TrackingLocationService : Service(), KoinComponent {
    val useCase: UseCase by inject()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= 26) {
            val CHANNEL_ID = "my_channel_01"
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_MIN
            )

            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                channel
            )

            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("WallX")
                .setAutoCancel(true)
                .setShowWhen(false)
                .setOngoing(false)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setContentText("").build()

            startForeground(1, notification)
        }
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LocationRequestGoogleHelper.getInstance()
            .build(this)
            .onChangeLocation(object : LocationCallback {
                override fun onChangeLocation(location: Location?) {
                    location?.let {
                        GlobalScope.async {
                            saveLocation(location.latitude, location.longitude)

                        }
                    }
                }

            }).requestTrackLocation()
//                delay(60 * 1000L)
//            }
        return super.onStartCommand(intent, flags, startId)

    }

    private suspend fun saveLocation(lat: Double, long: Double) {
        useCase.locationUC.saveLocation(
            LocationEntity(
                UUID.randomUUID().toString(),
                System.currentTimeMillis(),
                "LocationServices", lat, long
            )
        )
            .let {
                when (it) {
                    is Result.Success -> {
                        Log.e("WTF", "insert success ${it.data}")
                    }
                    is Result.Error -> {
                        Log.e("WTF", "insert fail")
                        it.exception.printStackTrace()

                    }
                    is Result.Loading -> {
                        Log.e("WTF", "insert loading ")

                    }
                }

            }
    }


}
