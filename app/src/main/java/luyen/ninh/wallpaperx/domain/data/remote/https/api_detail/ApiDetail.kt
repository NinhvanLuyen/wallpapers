package luyen.ninh.wallpaperx.domain.data.remote.https.api_detail

import luyen.ninh.wallpaperx.domain.data.remote.https.api.Api
import luyen.ninh.wallpaperx.domain.data.remote.https.api.HttpRequest
import luyen.ninh.wallpaperx.domain.data.remote.https.models.reponse.BaseResponse
import luyen.ninh.wallpaperx.domain.data.remote.https.models.reponse.PhotoRes

/**
 * Created by luyen_ninh on 2019-08-15.
 */
class ApiDetail(private val photoHTTP: HttpRequest, private var params: HashMap<String, String> = HashMap()) : Api {
    override suspend fun getListPhoto(urlNextPage: String): BaseResponse<List<PhotoRes>> =
        photoHTTP.getListHomePhotoNextPage(urlNextPage, params)

    override suspend fun getPhotoDetail(photoID: String): Any {
        val url = "https://api.unsplash.com/search/photos?"
        return photoHTTP.getPhotoDetail(url, params)
    }
}