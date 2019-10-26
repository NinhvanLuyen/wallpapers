package luyen.ninh.wallpaperx.domain.data.remote.https.models.reponse


class BaseResponse<T> {
    private var photos: T? = null
    private var next_page: String = ""
    private var errors: ArrayList<String> = arrayListOf()
    fun getUrlNextPage() = next_page
    fun getPhotos() = photos
    private var message: String? = null
    fun getErrors(): ArrayList<String>? = errors

}