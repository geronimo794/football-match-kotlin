package `in`.rozik.footballmatch.favoriteteamlist


import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.helpers.invisible
import `in`.rozik.footballmatch.system.helpers.visible
import `in`.rozik.footballmatch.system.models.FavoriteTeam
import `in`.rozik.footballmatch.system.models.Team
import `in`.rozik.footballmatch.teamdetail.TeamDetailActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import java.io.Serializable

class FavoriteTeamListFragment : Fragment(), FavoriteTeamListView {

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerViewFavoriteTeam: RecyclerView

    private lateinit var favoriteTeamListPresenter: FavoriteTeamListPresenter
    private lateinit var favoriteTeamAdapter: FavoriteTeamAdapter
    private var mutableListFavoriteTeam: MutableList<FavoriteTeam> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (container != null) {
            val retView: View? =
                FavoriteTeamListFragmentUI().createView(AnkoContext.create(container.context, container))
            mainFun()
            retView
        } else {
            TextView(activity).apply {
                setText(R.string.hello_blank_fragment)
            }
        }
    }

    private fun mainFun() {
        favoriteTeamListPresenter = FavoriteTeamListPresenter(this, this.context)
        favoriteTeamListPresenter.getFavoriteTeam()
        swipeRefresh.onRefresh {
            favoriteTeamListPresenter.getFavoriteTeam()
        }

        favoriteTeamAdapter = FavoriteTeamAdapter(mutableListFavoriteTeam) {
            startActivity<TeamDetailActivity>(
                getString(R.string.team) to reformatToTeam(it) as Serializable,
                getString(R.string.teamName) to it.name as String
            )
        }
        recyclerViewFavoriteTeam.adapter = favoriteTeamAdapter
    }

    private fun reformatToTeam(favoriteTeam: FavoriteTeam): Team {
        return Team(
            favoriteTeam.teamId,
            favoriteTeam.name,
            favoriteTeam.badge,
            favoriteTeam.formedYear,
            favoriteTeam.stadium,
            favoriteTeam.stadiumThumb,
            favoriteTeam.stadiumDescription,
            favoriteTeam.website,
            favoriteTeam.descriptionEN
        )
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showFavoriteTeamList(data: List<FavoriteTeam>?) {
        if (data != null) {
            swipeRefresh.isRefreshing = false
            mutableListFavoriteTeam.clear()
            mutableListFavoriteTeam.addAll(data)
            favoriteTeamAdapter.notifyDataSetChanged()
        }
    }

    inner class FavoriteTeamListFragmentUI : AnkoComponent<ViewGroup?> {

        override fun createView(ui: AnkoContext<ViewGroup?>): View {
            return with(ui) {
                verticalLayout {
                    swipeRefresh = swipeRefreshLayout {
                        setColorSchemeResources(
                            R.color.colorAccent, android.R.color.holo_green_light,
                            android.R.color.holo_orange_light, android.R.color.holo_red_light
                        )

                        relativeLayout {
                            lparams(width = matchParent, height = wrapContent)

                            recyclerViewFavoriteTeam = recyclerView {
                                id = R.id.recyclerViewFavoriteEvent
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
