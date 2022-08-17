package `in`.rozik.footballmatch.leaguelist

import `in`.rozik.footballmatch.system.models.League

interface LeagueListView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(data: List<League>)
}