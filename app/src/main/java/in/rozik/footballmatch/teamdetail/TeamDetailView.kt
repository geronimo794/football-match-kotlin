package `in`.rozik.footballmatch.teamdetail

import `in`.rozik.footballmatch.system.models.Team

interface TeamDetailView {
    fun showTeamDetail(data: Team)
}