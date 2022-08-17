package `in`.rozik.footballmatch.system.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Team(
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
    var descriptionEN: String? = null,

    @SerializedName("strSport")
    var sport: String? = null

) : Serializable