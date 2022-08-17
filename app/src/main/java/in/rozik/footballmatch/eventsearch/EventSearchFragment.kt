package `in`.rozik.footballmatch.eventsearch


import `in`.rozik.footballmatch.ApiRepository
import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.eventdetail.EventDetailActivity
import `in`.rozik.footballmatch.eventlist.EventListAdapter
import `in`.rozik.footballmatch.eventlist.EventListPresenter
import `in`.rozik.footballmatch.eventlist.EventListView
import `in`.rozik.footballmatch.system.helpers.invisible
import `in`.rozik.footballmatch.system.helpers.visible
import `in`.rozik.footballmatch.system.models.Event
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk15.coroutines.onQueryTextListener
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import java.io.Serializable

class EventSearchFragment : Fragment(), EventListView {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerViewEventList: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var searchView: SearchView

    private lateinit var eventListAdapter: EventListAdapter
    private lateinit var eventListPresenter: EventListPresenter
    private var mutableListEvent: MutableList<Event> = mutableListOf()
    private var keyword: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (container != null) {
            val retView: View? = EventListFragmentUI().createView(AnkoContext.create(container.context, container))
            mainFun()
            retView
        } else {
            TextView(activity).apply {
                setText(R.string.hello_blank_fragment)
            }
        }
    }

    private fun mainFun() {
        val showReminder = false
        val request = ApiRepository()
        val gson = Gson()

        progressBar.invisible()

        eventListAdapter = EventListAdapter(context, mutableListEvent, showReminder, {
            startActivity<EventDetailActivity>(
                getString(R.string.event) to it as Serializable
            )
        }, {})
        recyclerViewEventList.adapter = eventListAdapter

        eventListPresenter = EventListPresenter(this, request, gson)
        eventListPresenter.findSearchEvent(keyword)

        searchView.isIconified = false
        searchView.setIconifiedByDefault(false)
        searchView.onQueryTextListener {
            this.onQueryTextSubmit {
                keyword = it
                eventListPresenter.findSearchEvent(keyword)
                true
            }
            this.onQueryTextChange {
                progressBar.invisible()
                true
            }
        }
        swipeRefresh.onRefresh {
            eventListPresenter.findSearchEvent(keyword)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Event>?) {
        if (data !== null) {
            val filterData = data.filter { it.sport == "Soccer" }
            swipeRefresh.isRefreshing = false
            mutableListEvent.clear()
            mutableListEvent.addAll(filterData)
            eventListAdapter.notifyDataSetChanged()
        } else {
            swipeRefresh.isRefreshing = false
            mutableListEvent.clear()
            eventListAdapter.notifyDataSetChanged()
        }
    }

    inner class EventListFragmentUI : AnkoComponent<ViewGroup?> {

        override fun createView(ui: AnkoContext<ViewGroup?>): View {
            return with(ui) {
                verticalLayout {
                    searchView = searchView {
                        padding = dip(5)
                        id = R.id.searchViewPertandingan
                    }
                    swipeRefresh = swipeRefreshLayout {
                        setColorSchemeResources(
                            R.color.colorAccent, android.R.color.holo_green_light,
                            android.R.color.holo_orange_light, android.R.color.holo_red_light
                        )

                        relativeLayout {
                            lparams(width = matchParent, height = wrapContent)

                            recyclerViewEventList = recyclerView {
                                id = R.id.recyclerViewListEvent
                                lparams(width = matchParent, height = wrapContent)
                                layoutManager = LinearLayoutManager(ctx)
                            }

                            progressBar = progressBar {}.lparams {
                                centerHorizontally()
                            }
                        }
                    }

                }
            }
        }
    }


}
