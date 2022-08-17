package `in`.rozik.footballmatch.teamlogo

import `in`.rozik.footballmatch.system.models.Team

interface TeamLogoView {
    fun showLoading()
    fun hideLoading()
    fun showTeamLogo(team: List<Team>)
}