package luyen.ninh.wallpaperx.services

import android.annotation.SuppressLint
import android.app.IntentService
import android.content.Context
import android.content.Intent
import luyen.ninh.wallpaperx.domain.usecase.UseCase
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import luyen.ninh.wallpaperx.domain.data.local.room.entities.StepEntity


/**
 * Created by luyen_ninh on 2019-10-26.
 */
@SuppressLint("Registered")
class TrackingStepService : IntentService("TrackingStepService"), KoinComponent, SensorEventListener {
    private val useCase: UseCase by inject()

    @SuppressLint("LogNotTimber")
    override fun onSensorChanged(event: SensorEvent?) {
            Log.e("WTF","sensor event")
        if (event?.sensor?.type == Sensor.TYPE_STEP_DETECTOR) {
            Log.e("WTF","step ++")
            GlobalScope.async {
                useCase.stepUC.insertStep(StepEntity(System.currentTimeMillis()))
            }

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onHandleIntent(intent: Intent?) {
        Log.e("WTF","start-service-step-tracking")
        val sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val stepSensor = sManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        sManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST)

    }

}