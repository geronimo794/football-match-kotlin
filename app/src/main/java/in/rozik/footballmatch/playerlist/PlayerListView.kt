package `in`.rozik.footballmatch.playerlist

import `in`.rozik.footballmatch.system.models.Player

interface PlayerListView {
    fun showPlayers(data: List<Player>?)
    fun showLoading()
    fun hideLoading()
}