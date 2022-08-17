package `in`.rozik.footballmatch.playerlist


import `in`.rozik.footballmatch.ApiRepository
import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.playerdetail.PlayerDetailActivity
import `in`.rozik.footballmatch.system.helpers.invisible
import `in`.rozik.footballmatch.system.helpers.visible
import `in`.rozik.footballmatch.system.models.Player
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
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PlayerListFragment : Fragment(), PlayerListView {
    // Komponen
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerViewPlayer: RecyclerView
    private lateinit var progressBar: ProgressBar

    // Adapter presenter
    private lateinit var playerListPresenter: PlayerListPresenter
    private lateinit var playerListAdapter: PlayerListAdapter

    // Item data
    private var mutableListPlayer: MutableList<Player> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (container != null) {
            val retView: View? = PlayerListFragmentUI().createView(AnkoContext.create(container.context, container))
            mainFun()
            retView
        } else {
            return TextView(activity).apply {
                setText(R.string.hello_blank_fragment)
            }
        }
    }

    private fun mainFun() {
        val teamId = arguments?.getString(getString(R.string.teamId)) ?: "1"
        val request = ApiRepository()
        val gson = Gson()

        // Create presenter
        playerListPresenter = PlayerListPresenter(this, request, gson)
        playerListPresenter.getPlayerList(teamId)
        swipeRefresh.onRefresh { playerListPresenter.getPlayerList(teamId) }

        // Create recycleview adapter
        playerListAdapter = PlayerListAdapter(mutableListPlayer, this.context) {
            startActivity<PlayerDetailActivity>(
                (this.context?.getString(R.string.player) ?: "player") to it
            )
        }
        recyclerViewPlayer.adapter = playerListAdapter
    }

    override fun showPlayers(data: List<Player>?) {
        if (data != null) {
            swipeRefresh.isRefreshing = false
            mutableListPlayer.clear()
            mutableListPlayer.addAll(data)
            playerListAdapter.notifyDataSetChanged()
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    inner class PlayerListFragmentUI : AnkoComponent<ViewGroup?> {

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

                            recyclerViewPlayer = recyclerView {
                                id = R.id.recyclerViewPlayer
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
