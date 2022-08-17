package `in`.rozik.footballmatch.eventdetail

import `in`.rozik.footballmatch.system.models.Event

class EventDetailPresenter(private val view: EventDetailView, private val event: Event) {
    fun displayEvent() {
        view.showEventDetail(event)
    }
}