package luyen.ninh.wallpaperx.presentations.services

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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import luyen.ninh.wallpaperx.domain.data.local.room.entities.StepEntity


/**
 * Created by luyen_ninh on 2019-10-26.
 */
@SuppressLint("Registered")
class TrackingStepService : IntentService("TrackingStepService"), KoinComponent, SensorEventListener {
    private val useCase: UseCase by inject()

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_DETECTOR) {
            GlobalScope.async {
                useCase.stepUC.insertStep(StepEntity(System.currentTimeMillis()))
            }

        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onHandleIntent(intent: Intent?) {
        val sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val stepSensor = sManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        sManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_FASTEST)

    }

}