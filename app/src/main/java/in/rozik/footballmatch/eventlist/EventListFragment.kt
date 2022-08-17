package `in`.rozik.footballmatch.eventlist


import `in`.rozik.footballmatch.ApiRepository
import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.eventdetail.EventDetailActivity
import `in`.rozik.footballmatch.system.helpers.invisible
import `in`.rozik.footballmatch.system.helpers.visible
import `in`.rozik.footballmatch.system.models.Event
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 *
 */
class EventListFragment : Fragment(), EventListView {
    private var leagueId: String = ""
    private var jenisLaga: String = ""

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var rclvwListEvent: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapterEventList: EventListAdapter

    private var mutlsEvent: MutableList<Event> = mutableListOf()
    private lateinit var presenterEventList: EventListPresenter

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

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Event>?) {
        if (data !== null) {
            swipeRefresh.isRefreshing = false
            mutlsEvent.clear()
            mutlsEvent.addAll(data)
            adapterEventList.notifyDataSetChanged()
        }
    }

    private fun mainFun() {
        leagueId = arguments?.getString(getString(R.string.leagueId)) ?: "1"
        jenisLaga = arguments?.getString(getString(R.string.jenisLaga)) ?: getString(R.string.lampau)
        val showReminder = (jenisLaga == getString(R.string.mendatang))

        val request = ApiRepository()
        val gson = Gson()

        adapterEventList = EventListAdapter(context, mutlsEvent, showReminder, {
            startActivity<EventDetailActivity>(
                getString(R.string.event) to it as Serializable
            )
        }, {
            addToCalender(it)
        })
        rclvwListEvent.adapter = adapterEventList

        presenterEventList = EventListPresenter(this, request, gson)
        if (jenisLaga == getString(R.string.lampau)) {
            presenterEventList.getPreviousEvent(this.leagueId)
            swipeRefresh.onRefresh {
                presenterEventList.getPreviousEvent(this.leagueId)
            }
        } else {
            presenterEventList.getNextEvent(this.leagueId)
            swipeRefresh.onRefresh {
                presenterEventList.getNextEvent(this.leagueId)
            }

        }

    }

    private fun addToCalender(event: Event) {
        val calenderTitle = "${event.homeTeam} VS  ${event.awayTeam}"
        val calenderDescription = "Pertandingan sepak bola antara  ${event.homeTeam} dan ${event.awayTeam}"
        val fullCalendar = "${event.heldDate} ${event.heldTime}"
        Log.d("ELFfullCalendar", fullCalendar)

        var startTime = Date()
        // Get time start format for calender
        if (event.heldTime.toString().matches("\\d{2}:\\d{2}:\\d{2}\\+\\d{2}:\\d{2}".toRegex())) {
            val fmtInFull = SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH)
            fmtInFull.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
            startTime = fmtInFull.parse(fullCalendar)
            Log.d("ELFeventheldTimeFull", event.heldTime)
        } else {
            val calStart = GregorianCalendar()
            val fmtInFull = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
            fmtInFull.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
            startTime = fmtInFull.parse(fullCalendar)
            Log.d("ELFeventheldTimeFull", event.heldTime)
        }
        val calStart = GregorianCalendar()
        val calEnd = GregorianCalendar()

        calStart.time = startTime
        calEnd.time = startTime

        calEnd.add(Calendar.HOUR_OF_DAY, 2)
        Log.d("ELFcalStart", calStart.toString())
        Log.d("ELFcalEnd", calEnd.toString())

        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calStart.timeInMillis)
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calEnd.timeInMillis)
            .putExtra(CalendarContract.Events.TITLE, calenderTitle)
            .putExtra(CalendarContract.Events.DESCRIPTION, calenderDescription)
            .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
        startActivity(intent)
    }

    inner class EventListFragmentUI : AnkoComponent<ViewGroup?> {

        override fun createView(ui: AnkoContext<ViewGroup?>): View {
            return with(ui) {
                verticalLayout {
                    swipeRefresh = swipeRefreshLayout {
                        setColorSchemeResources(
                            R.color.colorAccent, android.R.color.holo_green_light,
                            android.R.color.holo_orange_light, android.R.color.holo_red_light
                        )

                        relativeLayout {
                            lparams(width = matchParent, height = wrapContent)

                            rclvwListEvent = recyclerView {
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



