package luyen.ninh.wallpaperx.domain.data.remote.https.models.reponse


/**
 * Created by ninhvanluyen on 1/11/18.
 */
data class PhotoRes(
    var id: String? = null,
    var created_at: String? = null,
    var updated_at: String? = null,
    var width: Int = 0,
    var height: Int = 0,
    var color: String? = null,
    var likes: Int = 0,
    var isLiked_by_user: Boolean = false,
    var description: String? = null,
    var isSponsored: Boolean = false,
    var user: User? = null,
    var urls: Urls? = null,
    var links: Links_? = null,
    var exif: Exif? = Exif()
)

