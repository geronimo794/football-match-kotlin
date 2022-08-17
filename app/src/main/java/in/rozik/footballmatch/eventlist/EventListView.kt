package `in`.rozik.footballmatch.eventlist

import `in`.rozik.footballmatch.system.models.Event

interface EventListView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Event>?)
}