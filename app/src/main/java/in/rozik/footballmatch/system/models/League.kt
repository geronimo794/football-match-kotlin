package `in`.rozik.footballmatch.system.models

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("idLeague")
    var leagueId: String = "",

    @SerializedName("strLeague")
    var leagueName: String? = null,

    @SerializedName("strBadge")
    var leagueBadge: String? = null
)