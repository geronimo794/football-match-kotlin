package `in`.rozik.footballmatch.favoriteeventlist

import `in`.rozik.footballmatch.system.helpers.CoroutineContextProvider
import `in`.rozik.footballmatch.system.helpers.EspressoIdle
import `in`.rozik.footballmatch.system.helpers.database
import `in`.rozik.footballmatch.system.models.FavoriteEvent
import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteEventListPresenter(
    private val view: FavoriteEventListView, private val context: Context?,
    private val corContext: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getFavoriteEvents() {
        EspressoIdle.increment()
        view.showLoading()
        GlobalScope.launch(corContext.main) {
            var listFavoriteEvent: List<FavoriteEvent>
            if (context != null) {
                context.database.use {
                    val result = select(FavoriteEvent.tableName)
                    listFavoriteEvent = result.parseList(classParser())
                    view.hideLoading()
                    view.showEventList(listFavoriteEvent)
                }
                EspressoIdle.decrement()
            }
        }
    }

}