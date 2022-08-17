package `in`.rozik.footballmatch.teamlist

import `in`.rozik.footballmatch.ApiRepository
import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.R.color.colorAccent
import `in`.rozik.footballmatch.system.helpers.invisible
import `in`.rozik.footballmatch.system.helpers.visible
import `in`.rozik.footballmatch.system.models.Team
import `in`.rozik.footballmatch.teamdetail.TeamDetailActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import java.io.Serializable

class TeamListActivity : AppCompatActivity(), TeamListView {
    private lateinit var leagueId: String
    private lateinit var leagueName: String
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var rclvwListTeam: RecyclerView
    private lateinit var adapter: TeamListAdapter
    private var mutlsTeam: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TeamListActivityUI().setContentView(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mainFun()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun mainFun() {
        val request = ApiRepository()
        val gson = Gson()
        leagueId = intent.getStringExtra("leagueId")
        leagueName = intent.getStringExtra(getString(R.string.leagueName))
        title = "Daftar Tim $leagueName"

        adapter = TeamListAdapter(mutlsTeam) {
            startActivity<TeamDetailActivity>(
                getString(R.string.team) to it as Serializable,
                getString(R.string.teamName) to it.name as String

            )

        }

        rclvwListTeam.adapter = adapter

        presenter = TeamListPresenter(this, request, gson)
        presenter.getTeamView(leagueId)
        swipeRefresh.onRefresh {
            presenter.getTeamView(leagueId)
        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>?) {
        if (data != null) {
            swipeRefresh.isRefreshing = false
            mutlsTeam.clear()
            mutlsTeam.addAll(data)

            adapter.notifyDataSetChanged()
        }
    }

    inner class TeamListActivityUI : AnkoComponent<TeamListActivity> {

        override fun createView(ui: AnkoContext<TeamListActivity>) = with(ui) {

            verticalLayout {
                // swipeRefresh block
                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(
                        colorAccent, android.R.color.holo_green_light,
                        android.R.color.holo_orange_light, android.R.color.holo_red_light
                    )

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        rclvwListTeam = recyclerView {
                            id = R.id.recyclerViewTeam
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
