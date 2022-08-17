package `in`.rozik.footballmatch.system.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FavoriteTeam(
    @SerializedName("id")
    var id: Long = 0,

    @SerializedName("idTeam")
    var teamId: String,

    @SerializedName("strTeam")
    var name: String? = null,

    @SerializedName("strTeamBadge")
    var badge: String? = null,

    @SerializedName("intFormedYear")
    var formedYear: String? = null,

    @SerializedName("strStadium")
    var stadium: String? = null,

    @SerializedName("strStadiumThumb")
    var stadiumThumb: String? = null,

    @SerializedName("strStadiumDescription")
    var stadiumDescription: String? = null,

    @SerializedName("strWebsite")
    var website: String? = null,

    @SerializedName("strDescriptionEN")
    var descriptionEN: String? = null

) : Serializable {
    companion object {
        const val tableName: String = "tblFavoriteTeam"
        const val id: String = "id"
        const val idTeam: String = "idTeam"
        const val name: String = "name"
        const val badge: String = "badge"
        const val formedYear: String = "formedYear"
        const val stadium: String = "stadium"
        const val stadiumThumb: String = "stadiumThumb"
        const val stadiumDescription: String = "stadiumDescription"
        const val website: String = "website"
        const val descriptionEN: String = "descriptionEN"
    }

}