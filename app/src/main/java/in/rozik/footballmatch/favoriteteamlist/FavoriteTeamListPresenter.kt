package `in`.rozik.footballmatch.favoriteteamlist

import `in`.rozik.footballmatch.system.helpers.CoroutineContextProvider
import `in`.rozik.footballmatch.system.helpers.EspressoIdle
import `in`.rozik.footballmatch.system.helpers.database
import `in`.rozik.footballmatch.system.models.FavoriteTeam
import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamListPresenter(
    private val view: FavoriteTeamListView, private val context: Context?,
    private val corContext: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getFavoriteTeam() {
        EspressoIdle.increment()
        view.showLoading()
        GlobalScope.launch(corContext.main) {
            var listFavoriteTeam: List<FavoriteTeam>
            if (context != null) {
                context.database.use {
                    val result = select(FavoriteTeam.tableName)
                    listFavoriteTeam = result.parseList(classParser())
                    view.hideLoading()
                    view.showFavoriteTeamList(listFavoriteTeam)
                }
                EspressoIdle.decrement()
            }
        }
    }

}