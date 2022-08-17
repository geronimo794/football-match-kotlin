package `in`.rozik.footballmatch.favoriteeventdetail

import `in`.rozik.footballmatch.system.models.FavoriteEvent

class FavoriteEventDetailPresenter(private val view: FavoriteEventDetailView, private val event: FavoriteEvent) {
    fun displayEvent() {
        view.showEventDetail(event)
    }
}