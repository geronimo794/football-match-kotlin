package `in`.rozik.footballmatch.eventdetail

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.R.drawable.ic_add_to_favorites
import `in`.rozik.footballmatch.R.drawable.ic_added_to_favorites
import `in`.rozik.footballmatch.R.id.itemAddToFavorites
import `in`.rozik.footballmatch.system.helpers.database
import `in`.rozik.footballmatch.system.models.Event
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
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class EventDetailActivity : AppCompatActivity(), EventDetailView {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var rclvwDetailEvent: RecyclerView
    private lateinit var evdtAdapter: EventDetailAdapter
    private lateinit var event: Event
    private lateinit var evdtPresenter: EventDetailPresenter
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventDetailActivityUI().setContentView(this)
        title = getString(R.string.detail_laga)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mainFun()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavoriteMenuIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            itemAddToFavorites -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavoriteMenuIcon()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mainFun() {
        event = intent.extras?.getSerializable(getString(R.string.event)) as Event

        Log.d("EDAevent", event.toString())
        evdtAdapter = EventDetailAdapter(this, event, supportFragmentManager)

        rclvwDetailEvent.adapter = evdtAdapter
        evdtPresenter = EventDetailPresenter(this, event)
        evdtPresenter.displayEvent()
        swipeRefresh.onRefresh {
            evdtPresenter.displayEvent()
        }
        getFavoriteState()
    }

    override fun showEventDetail(data: Event) {
        swipeRefresh.isRefreshing = false
        event = data
        evdtAdapter.notifyDataSetChanged()
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteEvent.tableName,
                    FavoriteEvent.idEvent to event.idEvent,
                    FavoriteEvent.heldDate to event.heldDate,
                    FavoriteEvent.heldTime to event.heldTime,
                    FavoriteEvent.homeTeamId to event.homeTeamId,
                    FavoriteEvent.awayTeamId to event.awayTeamId,
                    FavoriteEvent.homeTeam to event.homeTeam,
                    FavoriteEvent.awayTeam to event.awayTeam,
                    FavoriteEvent.homeScore to event.homeScore,
                    FavoriteEvent.awayScore to event.awayScore,
                    FavoriteEvent.homeShot to event.homeShot,
                    FavoriteEvent.awayShot to event.awayShot,
                    FavoriteEvent.homeRedCards to event.homeRedCards,
                    FavoriteEvent.awayRedCards to event.awayRedCards,
                    FavoriteEvent.homeYellowCards to event.homeYellowCards,
                    FavoriteEvent.awayYellowCards to event.awayYellowCards,
                    FavoriteEvent.homeLineupGoalkeeper to event.homeLineupGoalkeeper,
                    FavoriteEvent.awayLineupGoalkeeper to event.awayLineupGoalkeeper,
                    FavoriteEvent.homeLineupDefense to event.homeLineupDefense,
                    FavoriteEvent.awayLineupDefense to event.awayLineupDefense,
                    FavoriteEvent.homeLineupMidfield to event.homeLineupMidfield,
                    FavoriteEvent.homeGoalDetails to event.homeGoalDetails,
                    FavoriteEvent.awayLineupMidfield to event.awayLineupMidfield,
                    FavoriteEvent.awayGoalDetails to event.awayGoalDetails
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
                    getString(R.string.idEvent) to event.idEvent
                )
            }
            swipeRefresh.snackbar(getString(R.string.remove_favorite)).show()
        } catch (e: SQLiteConstraintException) {
            swipeRefresh.snackbar(e.localizedMessage).show()
        }
    }

    private fun getFavoriteState() {
        database.use {
            val result = select(FavoriteEvent.tableName)
                .whereArgs(
                    "(${FavoriteEvent.idEvent} = {idEvent})",
                    getString(R.string.idEvent) to event.idEvent
                )
            val favorite = result.parseList(classParser<FavoriteEvent>())
            Log.d("xxxEDA+favorite", favorite.toString())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavoriteMenuIcon() {
        Log.d("xxxEDA+isFavorite", isFavorite.toString())
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    inner class EventDetailActivityUI : AnkoComponent<EventDetailActivity> {

        override fun createView(ui: AnkoContext<EventDetailActivity>) = with(ui) {

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
