package `in`.rozik.footballmatch.eventlist

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.helpers.fullTimeFormatToDateWithDayGMT7
import `in`.rozik.footballmatch.system.helpers.fullTimeFormatToHourDayGMT7
import `in`.rozik.footballmatch.system.models.Event
import `in`.rozik.footballmatch.system.models.FavoriteEvent
import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class EventListAdapter(
    private val context: Context?,
    private val events: List<Event>,
    private val showReminderButton: Boolean,
    private val listener: (Event) -> Unit,
    private val listenerCalender: (Event) -> Unit
) : RecyclerView.Adapter<EventListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListViewHolder {
        return EventListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_event_cardview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        holder.bindItemEvent(events[position], listener, listenerCalender, showReminderButton)
    }

    override fun getItemCount(): Int = events.size
}


class EventListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val txtvwHomeTeam: TextView = view.find(R.id.txtvwHomeTeam)
    private val txtvwDate: TextView = view.find(R.id.txtvwDate)
    private val txtvwHour: TextView = view.find(R.id.txtvwHour)
    private val txtvwAwayTeam: TextView = view.find(R.id.txtvwAwayTeam)

    private val txtvwAwayScore: TextView = view.find(R.id.txtvwAwayScore)
    private val txtvwHomeScore: TextView = view.find(R.id.txtvwHomeScore)
    private val imgvwReminder: ImageView = view.find(R.id.setReminder)
    private val contentBlock: CardView = view.find(R.id.cardViewItemEvent)

    // Untuk binding data dari event yang diambil dari service
    fun bindItemEvent(
        events: Event,
        listener: (Event) -> Unit,
        listenerCalender: (Event) -> Unit,
        showReminderButton: Boolean
    ) {
        val strFullTime = events.heldDate + " " + events.heldTime
        if (showReminderButton) {
            imgvwReminder.visibility = View.VISIBLE
            imgvwReminder.onClick {
                listenerCalender(events)
            }
        } else {
            imgvwReminder.visibility = View.INVISIBLE
        }

        txtvwAwayTeam.text = events.awayTeam
        txtvwHomeTeam.text = events.homeTeam
        txtvwDate.text = strFullTime.fullTimeFormatToDateWithDayGMT7()
        txtvwHour.text = strFullTime.fullTimeFormatToHourDayGMT7()

        txtvwAwayScore.text = events.awayScore
        txtvwHomeScore.text = events.homeScore

        txtvwAwayTeam.onClick { listener(events) }
        txtvwHomeTeam.onClick { listener(events) }
        txtvwDate.onClick { listener(events) }
        txtvwAwayScore.onClick { listener(events) }
        txtvwHomeScore.onClick { listener(events) }
        contentBlock.onClick { listener(events) }

    }

    // Untuk binding data favorite event
    fun bindItemFavoriteEvent(
        events: FavoriteEvent,
        listener: (FavoriteEvent) -> Unit,
        showReminderButton: Boolean
    ) {
        val strFullTime = events.heldDate + " " + events.heldTime
        if (showReminderButton) {
            imgvwReminder.visibility = View.VISIBLE
        } else {
            imgvwReminder.visibility = View.INVISIBLE
        }
        txtvwAwayTeam.text = events.awayTeam
        txtvwHomeTeam.text = events.homeTeam
        txtvwDate.text = strFullTime.fullTimeFormatToDateWithDayGMT7()
        txtvwHour.text = strFullTime.fullTimeFormatToHourDayGMT7()

        txtvwAwayScore.text = events.awayScore
        txtvwHomeScore.text = events.homeScore

        txtvwAwayTeam.onClick { listener(events) }
        txtvwHomeTeam.onClick { listener(events) }
        txtvwDate.onClick { listener(events) }
        txtvwAwayScore.onClick { listener(events) }
        txtvwHomeScore.onClick { listener(events) }
        contentBlock.onClick { listener(events) }
    }
}

/**
 * RIP OLD CODE
 */
/*
1. Terlalu lama desain kalau tampilannya terlalu komplex
//return EventListViewHolder(EventListUI().createView(AnkoContext.create(parent.context, parent)))

class EventListUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            verticalLayout{
                lparams(width = matchParent, height = wrapContent)
                textView("22-02-2012"){
                    idEvent = R.idEvent.txtvwDate
                    gravity = Gravity.CENTER_HORIZONTAL
                }
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_HORIZONTAL

                    // Hometeam text
                    textView {
                        idEvent = R.idEvent.txtvwHomeTeam
                        textSize = 16f
                        text = "Home Team"
                    }.lparams{
                        margin = dip(15)
                        weight = 0.25f
                    }

                    // Hometeam score
                    textView {
                        idEvent = R.idEvent.txtvwHomeScore
                        textSize = 16f
                        text = "1"
                    }.lparams{
                        margin = dip(15)
                        weight = 0.20f
                    }

                    // VS
                    textView {
                        textSize = 16f
                        text = "VS"
                    }.lparams{
                        margin = dip(15)
                        weight = 0.10f
                    }

                    // Away score
                    textView {
                        idEvent = R.idEvent.txtvwAwayScore
                        textSize = 16f
                        text = "1"
                    }.lparams{
                        margin = dip(15)
                        weight = 0.20f
                    }

                    // Away text
                    textView {
                        idEvent = R.idEvent.txtvwAwayTeam
                        textSize = 16f
                        text = "Away Team"

                    }.lparams{
                        margin = dip(15)
                        weight = 0.25f
                    }

                }

            }


        }
    }

}
 */