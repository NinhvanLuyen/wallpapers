package luyen.ninh.wallpaperx

import android.app.Application
import luyen.ninh.wallpaperx.di.localModule
import luyen.ninh.wallpaperx.di.remoteModule
import luyen.ninh.wallpaperx.di.useCaseModule
import org.koin.android.ext.android.startKoin
import org.koin.core.Koin

/**
 * Created by luyen_ninh on 2019-10-13.
 */
class MyApplication:Application(){
    companion object{
        lateinit var app:Application
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        startKoin(this, listOf(localModule, remoteModule, useCaseModule))
    }
}