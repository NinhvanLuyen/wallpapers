package luyen.ninh.wallpaperx.domain.data.remote.https

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by luyen_ninh on 2019-08-19.
 */
class RequestInterceptor : Interceptor {
    private var mHeaders: MutableMap<String, String> = mutableMapOf()
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (mHeaders.isNotEmpty()) {
            mHeaders.forEach {
                builder.header(it.key, it.value)
            }
        }
        return chain.proceed(builder.build())
    }

    fun addHeader(key: String, value: String) {
        mHeaders[key] = value
    }

    fun removeHeader() {
        mHeaders = hashMapOf()
    }

}