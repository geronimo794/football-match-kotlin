package `in`.rozik.footballmatch.teamdescription

import `in`.rozik.footballmatch.system.models.Team

interface TeamDescriptionView {
    fun showTeamDescription(data: Team)
}