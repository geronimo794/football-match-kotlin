package `in`.rozik.footballmatch.eventdetail

import `in`.rozik.footballmatch.system.models.Event

interface EventDetailView {
    fun showEventDetail(data: Event)
}