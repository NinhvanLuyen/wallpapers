package luyen.ninh.wallpaperx.domain.data.remote.https.models.reponse

data class Exif(
    var make: String = "",
    var model: String = "",
    var aperture: String = "",
    var focal_length: String = "",
    var exposure_time: String = "",
    var iso: Int = 0
)