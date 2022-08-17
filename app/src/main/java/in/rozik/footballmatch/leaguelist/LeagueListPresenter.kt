package `in`.rozik.footballmatch.leaguelist

import `in`.rozik.footballmatch.ApiRepository
import `in`.rozik.footballmatch.TheSportDBApi
import `in`.rozik.footballmatch.system.helpers.CoroutineContextProvider
import `in`.rozik.footballmatch.system.helpers.EspressoIdle
import `in`.rozik.footballmatch.system.models.LeagueResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeagueListPresenter(
    private val view: LeagueListView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeagueList(country: String, sport: String) {
        EspressoIdle.increment()

        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getLeagues(country, sport)).await(),
                LeagueResponse::class.java
            )
            view.showLeagueList(data.countrys)
            view.hideLoading()
            EspressoIdle.decrement()

        }
        /*
        * Multi thread anko common
        */
        /*
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLeagues(country, sport)),
                LeagueResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showLeagueList(data.countrys)
            }
        }
        */
    }
}