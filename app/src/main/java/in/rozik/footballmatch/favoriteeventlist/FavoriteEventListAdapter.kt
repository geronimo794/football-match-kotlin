package `in`.rozik.footballmatch.favoriteeventlist

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.eventlist.EventListViewHolder
import `in`.rozik.footballmatch.system.models.FavoriteEvent
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class FavoriteEventListAdapter(
    private val context: Context?,
    private val events: List<FavoriteEvent>,
    private val listener: (FavoriteEvent) -> Unit
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
        holder.bindItemFavoriteEvent(events[position], listener, false)
    }

    override fun getItemCount(): Int = events.count()

}