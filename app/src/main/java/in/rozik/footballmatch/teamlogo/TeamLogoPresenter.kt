package `in`.rozik.footballmatch.teamlogo

import `in`.rozik.footballmatch.ApiRepository
import `in`.rozik.footballmatch.TheSportDBApi
import `in`.rozik.footballmatch.system.helpers.EspressoIdle
import `in`.rozik.footballmatch.system.models.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamLogoPresenter(
    private val view: TeamLogoView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getTeamView(teamId: String) {
        EspressoIdle.increment()
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(teamId)).await(),
                TeamResponse::class.java
            )
            view.hideLoading()
            view.showTeamLogo(data.teams)
            EspressoIdle.decrement()
        }

    }
}