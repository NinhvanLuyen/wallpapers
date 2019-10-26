package luyen.ninh.wallpaperx.domain.data.remote.https.models.reponse


/**
 * Created by ninhvanluyen on 1/29/18.
 */

data class User(
    var id: String? = null,
    var updated_at: String? = null,
    var username: String? = null,
    var name: String? = null,
    var first_name: String? = null,
    var last_name: String? = null,
    var twitter_username: String? = null,
    var portfolio_url: String? = null,
    var bio: String? = null,
    var location: String? = null,
    var total_likes: Int = 0,
    var total_photos: Int = 0,
    var total_collections: Int = 0,
    var profile_image: Profile_Image? = null,
    var links: Links? = null
)