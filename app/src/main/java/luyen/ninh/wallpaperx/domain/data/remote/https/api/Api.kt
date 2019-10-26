package luyen.ninh.wallpaperx.domain.data.remote.https.api

/**
 * Created by luyen_ninh on 2019-08-15.
 */
interface Api {
    suspend fun getListPhoto(urlNextPage: String): Any
    suspend fun getPhotoDetail(photoID: String): Any
}