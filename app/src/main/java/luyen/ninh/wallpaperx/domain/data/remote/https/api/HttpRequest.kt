package luyen.ninh.wallpaperx.domain.data.remote.https.api

import luyen.ninh.wallpaperx.domain.data.remote.https.models.reponse.BaseResponse
import luyen.ninh.wallpaperx.domain.data.remote.https.models.reponse.PhotoRes
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

/**
 * Created by luyen_ninh on 2019-08-19.
 */
interface HttpRequest {

//    @GET("search")
//    fun getRelateSearch(@QueryMap option: Map<String, String>): Deferred<BaseRelateSearch>
//
//    @GET("feeds/home")
//    fun getListHomePhoto(@QueryMap option: Map<String, String>): Deferred<BaseResponse<List<PhotoRes>>>
//

    @GET
    suspend fun getListHomePhotoNextPage(@Url url: String, @QueryMap option: Map<String, String>): BaseResponse<List<PhotoRes>>

    @GET()
    suspend fun getPhotoDetail(@Url url: String, @QueryMap option: Map<String, String>): PhotoRes


//    @GET()
//    fun getPhotoCate(@Url url: String, @QueryMap option: Map<String, String>): Deferred<BaseCateRes>
//
//    @GET()
//    fun getHomePhotos(@Url url: String, @QueryMap option: Map<String, String>): Deferred<List<PhotoRes>>
}