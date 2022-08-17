package `in`.rozik.footballmatch.playerdetail

import `in`.rozik.footballmatch.system.models.Player

interface PlayerDetailView {
    fun showPlayerDetail(data: Player)
}