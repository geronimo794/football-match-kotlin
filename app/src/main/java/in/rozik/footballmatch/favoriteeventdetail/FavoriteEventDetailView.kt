package `in`.rozik.footballmatch.favoriteeventdetail

import `in`.rozik.footballmatch.system.models.FavoriteEvent

interface FavoriteEventDetailView {
    fun showEventDetail(data: FavoriteEvent)
}