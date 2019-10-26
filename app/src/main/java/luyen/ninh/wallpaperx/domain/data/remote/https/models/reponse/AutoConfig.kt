package luyen.ninh.wallpaperx.domain.data.remote.https.models.reponse

/**
 * Created by ninhvanluyen on 3/1/18.
 */
class AutoConfig {
    var enableAutoFromSource: Boolean = false
    var enableAutoFromAI: Boolean = false
    var interval: Long = 0
    var source: Int = 0
    var wifiOnly: Boolean= false
}