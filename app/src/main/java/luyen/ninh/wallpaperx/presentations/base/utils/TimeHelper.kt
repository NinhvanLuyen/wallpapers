package luyen.ninh.wallpaperx.presentations.base.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by luyen_ninh on 2019-10-17.
 */
object TimeHelper
{
    fun toString(time: Long): String {
        val isoFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss ", Locale.getDefault())
        return isoFormat.format(Date(time))
    }
    fun getStartTimeOfDay(day: Int, month: Int, year: Int): Long {
        val cal = Calendar.getInstance()
        cal.set(year, month, day)
        cal.set(Calendar.MONTH, month - 1)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }

    fun getEndTimeOfDay(day: Int, month: Int, year: Int): Long {
        val cal = Calendar.getInstance()
        cal.set(year, month, day)
        cal.set(Calendar.MONTH, month - 1)
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.set(Calendar.MILLISECOND, 999)
        return cal.timeInMillis
    }
    private fun getCurrentTime(): Date {
        return Date(System.currentTimeMillis())
    }

    fun getStartTimeOfToday(): Long {
        val cal = Calendar.getInstance()
        cal.time = getCurrentTime()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.timeInMillis
    }

    fun getEndTimeOfToDay(): Long {
        val cal = Calendar.getInstance()
        cal.time = getCurrentTime()
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.set(Calendar.MILLISECOND, 999)
        return cal.timeInMillis
    }
}