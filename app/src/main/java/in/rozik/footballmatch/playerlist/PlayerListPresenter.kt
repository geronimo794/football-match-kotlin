package `in`.rozik.footballmatch.playerlist

import `in`.rozik.footballmatch.ApiRepository
import `in`.rozik.footballmatch.TheSportDBApi
import `in`.rozik.footballmatch.system.helpers.EspressoIdle
import `in`.rozik.footballmatch.system.models.PlayerResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerListPresenter(
    private val view: PlayerListView, private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getPlayerList(teamId: String) {
        EspressoIdle.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getPlayersByTeamId(teamId)).await(),
                PlayerResponse::class.java
            )
            view.showPlayers(data.player)
            view.hideLoading()
            EspressoIdle.decrement()
        }
    }
}