package `in`.rozik.footballmatch.leaguelist

import `in`.rozik.footballmatch.ApiRepository
import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.R.color.colorAccent
import `in`.rozik.footballmatch.eventbottomnavigation.EventBottomNavigationActivity
import `in`.rozik.footballmatch.favoritetabs.FavoriteTabsActivity
import `in`.rozik.footballmatch.searchtabs.SearchTabsActivity
import `in`.rozik.footballmatch.system.helpers.invisible
import `in`.rozik.footballmatch.system.helpers.visible
import `in`.rozik.footballmatch.system.models.League
import `in`.rozik.footballmatch.teamlist.TeamListActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LeagueListActivity : AppCompatActivity(), LeagueListView {
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var rclvwListLeague: RecyclerView
    private lateinit var adapter: MainAdapter
    private var mutlsLeagues: MutableList<League> = mutableListOf()
    private lateinit var presenter: LeagueListPresenter
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        mainFun()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.liga_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemFavoriteList -> {
                startActivity<FavoriteTabsActivity>()
                true
            }
            R.id.itemSearchData -> {
                startActivity<SearchTabsActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mainFun() {
        val request = ApiRepository()
        val gson = Gson()
        adapter = MainAdapter(mutlsLeagues, {
            startActivity<EventBottomNavigationActivity>(
                getString(R.string.leagueId) to it.leagueId,
                getString(R.string.leagueName) to it.leagueName
            )
        }, {
            startActivity<TeamListActivity>(
                getString(R.string.leagueId) to it.leagueId,
                getString(R.string.leagueName) to it.leagueName
            )
        })

        rclvwListLeague.adapter = adapter

        presenter = LeagueListPresenter(this, request, gson)
        presenter.getLeagueList(
            resources.getString(R.string.league_country),
            resources.getString(R.string.league_sport)
        )
        swipeRefresh.onRefresh {
            presenter.getLeagueList(
                resources.getString(R.string.league_country), resources.getString(
                    R.string.league_sport
                )
            )
        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLeagueList(data: List<League>) {
        swipeRefresh.isRefreshing = false
        val newData = data.filter {
            it.leagueName == "1"
        }
        mutlsLeagues.clear()
        mutlsLeagues.addAll(data)
        Log.d("xxxMA+mutlsLeagues", mutlsLeagues.toString())

        adapter.notifyDataSetChanged()
    }

    inner class MainActivityUI : AnkoComponent<LeagueListActivity> {

        override fun createView(ui: AnkoContext<LeagueListActivity>) = with(ui) {

            verticalLayout {
                // textView block
                textView(getString(R.string.pilih_liga)) {
                    textSize = 20f
                    gravity = Gravity.CENTER
                }.lparams(width = matchParent, height = wrapContent) {
                    topMargin = dip(20)
                }

                // swipeRefresh block
                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(
                        colorAccent, android.R.color.holo_green_light,
                        android.R.color.holo_orange_light, android.R.color.holo_red_light
                    )

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        rclvwListLeague = recyclerView {
                            id = R.id.recyclerViewLiga
                            lparams(width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }

                        progressBar = progressBar {}.lparams {
                            centerHorizontally()
                        }
                    }
                }
            }

        }
    }
}
