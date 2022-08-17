package `in`.rozik.footballmatch.favoriteeventlist

import `in`.rozik.footballmatch.system.models.FavoriteEvent

interface FavoriteEventListView {
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<FavoriteEvent>)
}