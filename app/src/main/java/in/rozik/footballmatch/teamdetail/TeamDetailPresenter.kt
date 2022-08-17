package `in`.rozik.footballmatch.teamdetail

import `in`.rozik.footballmatch.system.models.Team

class TeamDetailPresenter(private val view: TeamDetailView, private val team: Team) {
    fun displayTeam() {
        view.showTeamDetail(team)
    }
}