package `in`.rozik.footballmatch.teamlist

import `in`.rozik.footballmatch.system.models.Team

interface TeamListView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(team: List<Team>?)
}