package `in`.rozik.footballmatch.teamlist

import `in`.rozik.footballmatch.ApiRepository
import `in`.rozik.footballmatch.TheSportDBApi
import `in`.rozik.footballmatch.system.helpers.EspressoIdle
import `in`.rozik.footballmatch.system.models.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamListPresenter(
    private val view: TeamListView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getTeamView(leagueId: String) {
        EspressoIdle.increment()
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamsByLeagueId(leagueId)).await(),
                TeamResponse::class.java
            )
            view.hideLoading()
            view.showTeamList(data.teams)
            EspressoIdle.decrement()
        }
    }

    fun getSearchTeamView(keyword: String?) {
        if (keyword != null && keyword != "") {
            EspressoIdle.increment()
            view.showLoading()
            GlobalScope.launch(Dispatchers.Main) {
                val data = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getSearchTeam(keyword)).await(),
                    TeamResponse::class.java
                )
                view.hideLoading()
                view.showTeamList(data.teams)
                EspressoIdle.decrement()
            }
        }
    }
}