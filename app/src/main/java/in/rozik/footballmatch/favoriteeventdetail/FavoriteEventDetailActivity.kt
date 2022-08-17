package `in`.rozik.footballmatch.favoriteeventdetail

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.helpers.database
import `in`.rozik.footballmatch.system.models.FavoriteEvent
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteEventDetailActivity : AppCompatActivity(), FavoriteEventDetailView {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var rclvwDetailEvent: RecyclerView
    private lateinit var adapterFavoriteEventDetail: FavoriteEventDetailAdapter
    private lateinit var presenterFavoriteEventDetail: FavoriteEventDetailPresenter
    private lateinit var favoriteEvent: FavoriteEvent

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FavoriteEventDetailActivityUI().setContentView(this)
        title = getString(R.string.detail_laga_favorit)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mainFun()
    }

    override fun showEventDetail(data: FavoriteEvent) {
        swipeRefresh.isRefreshing = false
        favoriteEvent = data
        adapterFavoriteEventDetail.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {

                finish()
                true
            }
            R.id.itemAddToFavorites -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavoriteMenuIcon()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteEvent.tableName,
                    FavoriteEvent.idEvent to favoriteEvent.id,
                    FavoriteEvent.heldDate to favoriteEvent.heldDate,
                    FavoriteEvent.homeTeamId to favoriteEvent.homeTeamId,
                    FavoriteEvent.awayTeamId to favoriteEvent.awayTeamId,
                    FavoriteEvent.homeTeam to favoriteEvent.homeTeam,
                    FavoriteEvent.awayTeam to favoriteEvent.awayTeam,
                    FavoriteEvent.homeScore to favoriteEvent.homeScore,
                    FavoriteEvent.awayScore to favoriteEvent.awayScore,
                    FavoriteEvent.homeShot to favoriteEvent.homeShot,
                    FavoriteEvent.awayShot to favoriteEvent.awayShot,
                    FavoriteEvent.homeRedCards to favoriteEvent.homeRedCards,
                    FavoriteEvent.awayRedCards to favoriteEvent.awayRedCards,
                    FavoriteEvent.homeYellowCards to favoriteEvent.homeYellowCards,
                    FavoriteEvent.awayYellowCards to favoriteEvent.awayYellowCards,
                    FavoriteEvent.homeLineupGoalkeeper to favoriteEvent.homeLineupGoalkeeper,
                    FavoriteEvent.awayLineupGoalkeeper to favoriteEvent.awayLineupGoalkeeper,
                    FavoriteEvent.homeLineupDefense to favoriteEvent.homeLineupDefense,
                    FavoriteEvent.awayLineupDefense to favoriteEvent.awayLineupDefense,
                    FavoriteEvent.homeLineupMidfield to favoriteEvent.homeLineupMidfield,
                    FavoriteEvent.homeGoalDetails to favoriteEvent.homeGoalDetails,
                    FavoriteEvent.awayLineupMidfield to favoriteEvent.awayLineupMidfield,
                    FavoriteEvent.awayGoalDetails to favoriteEvent.awayGoalDetails
                )

            }
            swipeRefresh.snackbar(getString(R.string.add_favorite)).show()
        } catch (e: SQLiteConstraintException) {
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteEvent.tableName, "(${FavoriteEvent.idEvent} = {idEvent})",
                    getString(R.string.idEvent) to favoriteEvent.idEvent
                )
            }
            swipeRefresh.snackbar(getString(R.string.remove_favorite)).show()
        } catch (e: SQLiteConstraintException) {
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavoriteMenuIcon()
        return true
    }

    private fun getFavoriteState() {
        database.use {
            val result = select(FavoriteEvent.tableName)
                .whereArgs(
                    "(${FavoriteEvent.idEvent} = {idEvent})",
                    getString(R.string.idEvent) to favoriteEvent.idEvent
                )
            val favorite = result.parseList(classParser<FavoriteEvent>())
            Log.d("xxxFEDA+favorite", favorite.toString())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavoriteMenuIcon() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun mainFun() {
        favoriteEvent = intent.extras?.getSerializable(getString(R.string.favoriteEvent)) as FavoriteEvent

        adapterFavoriteEventDetail = FavoriteEventDetailAdapter(this, favoriteEvent, supportFragmentManager)

        rclvwDetailEvent.adapter = adapterFavoriteEventDetail
        presenterFavoriteEventDetail = FavoriteEventDetailPresenter(this, favoriteEvent)
        presenterFavoriteEventDetail.displayEvent()
        getFavoriteState()
    }

    inner class FavoriteEventDetailActivityUI : AnkoComponent<FavoriteEventDetailActivity> {

        override fun createView(ui: AnkoContext<FavoriteEventDetailActivity>) = with(ui) {

            verticalLayout {

                // swipeRefresh block
                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(
                        R.color.colorAccent, android.R.color.holo_green_light,
                        android.R.color.holo_orange_light, android.R.color.holo_red_light
                    )

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        rclvwDetailEvent = recyclerView {
                            lparams(width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

                    }
                }
            }

        }
    }

}
