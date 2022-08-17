package `in`.rozik.footballmatch.favoriteteamlist

import `in`.rozik.footballmatch.system.models.FavoriteTeam

interface FavoriteTeamListView {
    fun showLoading()
    fun hideLoading()
    fun showFavoriteTeamList(data: List<FavoriteTeam>?)
}