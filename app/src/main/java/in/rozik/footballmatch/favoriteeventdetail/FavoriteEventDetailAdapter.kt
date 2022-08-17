package `in`.rozik.footballmatch.favoriteeventdetail

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.eventdetail.EventDetailViewHolder
import `in`.rozik.footballmatch.system.models.FavoriteEvent
import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class FavoriteEventDetailAdapter(
    private val context: Context,
    private val event: FavoriteEvent,
    private val fmManager: FragmentManager
) :
    RecyclerView.Adapter<EventDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDetailViewHolder {
        return EventDetailViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_event_detail,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventDetailViewHolder, position: Int) {
        holder.bindItemFavoriteEvent(event, context, fmManager)

    }

    override fun getItemCount(): Int = 1
}

