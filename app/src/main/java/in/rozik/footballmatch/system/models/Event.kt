package `in`.rozik.footballmatch.system.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Event(
    @SerializedName("idEvent")
    var idEvent: String = "",

    @SerializedName("dateEvent")
    var heldDate: String? = null,

    @SerializedName("strTime")
    var heldTime: String? = null,

    @SerializedName("idHomeTeam")
    var homeTeamId: String? = null,

    @SerializedName("idAwayTeam")
    var awayTeamId: String? = null,

    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null,

    @SerializedName("intHomeShots")
    var homeShot: String? = null,

    @SerializedName("intAwayShots")
    var awayShot: String? = null,

    @SerializedName("strHomeRedCards")
    var homeRedCards: String? = null,

    @SerializedName("strAwayRedCards")
    var awayRedCards: String? = null,

    @SerializedName("strHomeYellowCards")
    var homeYellowCards: String? = null,

    @SerializedName("strAwayYellowCards")
    var awayYellowCards: String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var homeLineupGoalkeeper: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var awayLineupGoalkeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var homeLineupDefense: String? = null,

    @SerializedName("strAwayLineupDefense")
    var awayLineupDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var homeLineupMidfield: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var awayLineupMidfield: String? = null,

    @SerializedName("strHomeGoalDetails")
    var homeGoalDetails: String? = null,

    @SerializedName("strAwayGoalDetails")
    var awayGoalDetails: String? = null,

    @SerializedName("strSport")
    var sport: String? = null
) : Serializable