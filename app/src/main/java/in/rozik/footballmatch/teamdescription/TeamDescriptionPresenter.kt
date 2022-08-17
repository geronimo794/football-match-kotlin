package `in`.rozik.footballmatch.teamdescription

import `in`.rozik.footballmatch.system.models.Team

class TeamDescriptionPresenter(private val view: TeamDescriptionView, private val team: Team) {
    fun displayTeam() {
        view.showTeamDescription(team)
    }
}