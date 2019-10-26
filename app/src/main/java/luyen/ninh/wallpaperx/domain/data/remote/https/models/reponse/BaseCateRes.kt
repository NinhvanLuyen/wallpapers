package luyen.ninh.wallpaperx.domain.data.remote.https.models.reponse

class BaseCateRes {
    var total = 0
    var total_pages = 0
    var results: ArrayList<PhotoRes> = arrayListOf()
    fun getTottal() =total
    fun getTottalPages() = total_pages
    fun getPhotos() = results
}

//class PhotoRes {
//    var total = 0
//    var total_pages = 0
//    var results: ArrayList<Photo> = arrayListOf()
//}