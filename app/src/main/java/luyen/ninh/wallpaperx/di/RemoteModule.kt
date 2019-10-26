package luyen.ninh.wallpaperx.di


import luyen.ninh.wallpaperx.BuildConfig
import luyen.ninh.wallpaperx.domain.data.remote.LocationRemoteDataSource
import luyen.ninh.wallpaperx.domain.data.remote.StepRemoteDataSource
import luyen.ninh.wallpaperx.domain.data.remote.https.RequestInterceptor
import luyen.ninh.wallpaperx.domain.data.remote.https.api.Api
import luyen.ninh.wallpaperx.domain.data.remote.https.api.HttpRequest
import luyen.ninh.wallpaperx.domain.data.remote.https.api_detail.ApiDetail
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by luyen_ninh on 2019-08-19.
 */

val remoteModule = module {

    single { createInterceptorToken() }
    single { createOkHttpClient(get()) }
    single { createApiService(get()) }
    single { createWebService<HttpRequest>(get()) }

    // remote data source
    factory { createLocationRemoteDataSource(get()) }
    factory { createRemoteDataSource(get()) }

}


private fun createOkHttpClient(mInterceptor: RequestInterceptor): OkHttpClient {
    val timeOut = 1L
    val builder = OkHttpClient.Builder()
        .connectTimeout(timeOut, TimeUnit.MINUTES)
        .readTimeout(timeOut, TimeUnit.MINUTES)
        .addInterceptor(mInterceptor)
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)
    }
    return builder.build()

}

private fun createApiService(httpRequest: HttpRequest): Api = ApiDetail(httpRequest)

private fun createInterceptorToken(): RequestInterceptor {
    val mInterceptor = RequestInterceptor()
    mInterceptor.addHeader("Authorization", BuildConfig.TOKEN)
    mInterceptor.addHeader(
        "User-Agent", BuildConfig.USER_AGENT
    )
    mInterceptor.addHeader("Viewport-Width", "1080")
    return mInterceptor
}


inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.HOST)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

//Remote data source

//1. location
private fun createLocationRemoteDataSource(api:Api): LocationRemoteDataSource = LocationRemoteDataSource(api)

//2. step
private fun createRemoteDataSource(api: Api):StepRemoteDataSource = StepRemoteDataSource(api)