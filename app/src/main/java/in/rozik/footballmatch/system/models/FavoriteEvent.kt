package `in`.rozik.footballmatch.system.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FavoriteEvent(
    @SerializedName("id")
    var id: Long = 0,

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
    var awayGoalDetails: String? = null
) : Serializable {
    companion object {
        const val tableName: String = "tblFavoriteEvent"
        const val id: String = "id"
        const val idEvent: String = "idEvent"
        const val heldDate: String = "heldDate"
        const val heldTime: String = "heldTime"
        const val homeTeamId: String = "homeTeamId"
        const val awayTeamId: String = "awayTeamId"
        const val homeTeam: String = "homeTeam"
        const val awayTeam: String = "awayTeam"
        const val homeScore: String = "homeScore"
        const val awayScore: String = "awayScore"
        const val homeShot: String = "homeShot"
        const val awayShot: String = "awayShot"
        const val homeRedCards: String = "homeRedCards"
        const val awayRedCards: String = "awayRedCards"
        const val homeYellowCards: String = "homeYellowCards"
        const val awayYellowCards: String = "awayYellowCards"
        const val homeLineupGoalkeeper: String = "homeLineupGoalkeeper"
        const val awayLineupGoalkeeper: String = "awayLineupGoalkeeper"
        const val homeLineupDefense: String = "homeLineupDefense"
        const val awayLineupDefense: String = "awayLineupDefense"
        const val homeLineupMidfield: String = "homeLineupMidfield"
        const val awayLineupMidfield: String = "awayLineupMidfield"
        const val homeGoalDetails: String = "homeGoalDetails"
        const val awayGoalDetails: String = "awayGoalDetails"
    }
}