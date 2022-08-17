package `in`.rozik.footballmatch.playerdetail

import `in`.rozik.footballmatch.system.helpers.EspressoIdle
import `in`.rozik.footballmatch.system.models.Player


class PlayerDetailPresenter(private val playerDetailView: PlayerDetailView) {
    fun setPlayerDetail(player: Player) {
        EspressoIdle.increment()
        playerDetailView.showPlayerDetail(player)
        EspressoIdle.decrement()
    }
}