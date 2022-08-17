package `in`.rozik.footballmatch.eventlist

import `in`.rozik.footballmatch.ApiRepository
import `in`.rozik.footballmatch.TheSportDBApi
import `in`.rozik.footballmatch.system.helpers.EspressoIdle
import `in`.rozik.footballmatch.system.models.EventResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EventListPresenter(
    private val view: EventListView, private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getPreviousEvent(leagueId: String) {
        EspressoIdle.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getPreviousEvent(leagueId)).await(),
                EventResponse::class.java
            )
            view.showEventList(data.events)
            view.hideLoading()
            EspressoIdle.decrement()
        }
    }

    fun getNextEvent(leagueId: String) {
        EspressoIdle.increment()
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getNextEvent(leagueId)).await(),
                EventResponse::class.java
            )
            view.showEventList(data.events)
            view.hideLoading()
            EspressoIdle.decrement()
        }
    }

    fun findSearchEvent(keyword: String?) {
        if (keyword !== null && keyword != "") {
            EspressoIdle.increment()
            view.showLoading()
            GlobalScope.launch(Dispatchers.Main) {
                val data =
                    gson.fromJson(
                        apiRepository.doRequest(TheSportDBApi.getSearchEvent(keyword)).await(),
                        EventResponse::class.java
                    )
                view.showEventList(data.event)
                view.hideLoading()
                EspressoIdle.decrement()

            }
        }
    }

}