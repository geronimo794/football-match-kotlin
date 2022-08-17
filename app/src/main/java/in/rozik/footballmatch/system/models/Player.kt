package `in`.rozik.footballmatch.system.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Player(
    @SerializedName("strPlayer")
    var name: String? = null,

    @SerializedName("dateBorn")
    var born: String? = null,

    @SerializedName("dateSigned")
    var signed: String? = null,

    @SerializedName("strBirthLocation")
    var birthLocation: String? = null,

    @SerializedName("strDescriptionEN")
    var descriptionEN: String? = null,

    @SerializedName("strPosition")
    var position: String? = null,

    @SerializedName("strHeight")
    var height: String? = null,

    @SerializedName("strWeight")
    var weight: String? = null,

    @SerializedName("strCutout")
    var imageCutout: String? = null,

    @SerializedName("strThumb")
    var imageThumb: String? = null
) : Serializable