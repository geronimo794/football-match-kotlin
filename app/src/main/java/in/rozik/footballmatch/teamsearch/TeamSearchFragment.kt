package `in`.rozik.footballmatch.teamsearch


import `in`.rozik.footballmatch.ApiRepository
import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.helpers.invisible
import `in`.rozik.footballmatch.system.helpers.visible
import `in`.rozik.footballmatch.system.models.Team
import `in`.rozik.footballmatch.teamdetail.TeamDetailActivity
import `in`.rozik.footballmatch.teamlist.TeamListAdapter
import `in`.rozik.footballmatch.teamlist.TeamListPresenter
import `in`.rozik.footballmatch.teamlist.TeamListView
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk15.coroutines.onQueryTextListener
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import java.io.Serializable

class TeamSearchFragment : Fragment(), TeamListView {

    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerViewEventList: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var searchView: SearchView

    private lateinit var teamListAdapter: TeamListAdapter
    private lateinit var teamListPresenter: TeamListPresenter
    private var mutableListTeam: MutableList<Team> = mutableListOf()
    private var keyword: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (container != null) {
            val retView: View? = TeamListFragmentUI().createView(AnkoContext.create(container.context, container))
            mainFun()
            retView
        } else {
            TextView(activity).apply {
                setText(R.string.hello_blank_fragment)
            }
        }
    }

    private fun mainFun() {
        val request = ApiRepository()
        val gson = Gson()

        progressBar.invisible()

        teamListAdapter = TeamListAdapter(mutableListTeam) {
            startActivity<TeamDetailActivity>(
                getString(R.string.team) to it as Serializable,
                getString(R.string.teamName) to it.name
            )
        }
        recyclerViewEventList.adapter = teamListAdapter

        teamListPresenter = TeamListPresenter(this, request, gson)
        teamListPresenter.getSearchTeamView(keyword)

        searchView.isIconified = false
        searchView.setIconifiedByDefault(false)
        searchView.onQueryTextListener {
            this.onQueryTextSubmit {
                keyword = it
                teamListPresenter.getSearchTeamView(keyword)
                true
            }
            this.onQueryTextChange {
                progressBar.invisible()
                true
            }
        }
        swipeRefresh.onRefresh {
            teamListPresenter.getSearchTeamView(keyword)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>?) {
        if (data !== null) {
            val filterData = data.filter { it.sport == "Soccer" }
            swipeRefresh.isRefreshing = false
            mutableListTeam.clear()
            mutableListTeam.addAll(filterData)
            teamListAdapter.notifyDataSetChanged()
        } else {
            swipeRefresh.isRefreshing = false
            mutableListTeam.clear()
            teamListAdapter.notifyDataSetChanged()
        }
    }

    inner class TeamListFragmentUI : AnkoComponent<ViewGroup?> {

        override fun createView(ui: AnkoContext<ViewGroup?>): View {
            return with(ui) {
                verticalLayout {
                    searchView = searchView {
                        padding = dip(5)
                        id = R.id.searchViewTim
                    }
                    swipeRefresh = swipeRefreshLayout {
                        setColorSchemeResources(
                            R.color.colorAccent, android.R.color.holo_green_light,
                            android.R.color.holo_orange_light, android.R.color.holo_red_light
                        )

                        relativeLayout {
                            lparams(width = matchParent, height = wrapContent)

                            recyclerViewEventList = recyclerView {
                                id = R.id.recyclerViewListTeam
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


}
