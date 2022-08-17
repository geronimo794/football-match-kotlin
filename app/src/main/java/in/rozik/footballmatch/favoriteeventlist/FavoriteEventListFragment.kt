package `in`.rozik.footballmatch.favoriteeventlist


import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.favoriteeventdetail.FavoriteEventDetailActivity
import `in`.rozik.footballmatch.system.helpers.invisible
import `in`.rozik.footballmatch.system.helpers.visible
import `in`.rozik.footballmatch.system.models.FavoriteEvent
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

class FavoriteEventListFragment : Fragment(), FavoriteEventListView {

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var recyclerViewFavoriteEvent: RecyclerView

    private lateinit var adapterFavoriteEventList: FavoriteEventListAdapter
    private lateinit var presenterFavoriteEventList: FavoriteEventListPresenter
    private var mutableListFavoriteEvent: MutableList<FavoriteEvent> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (container != null) {
            val retView: View? =
                FavoriteEventListFragmentUI().createView(AnkoContext.create(container.context, container))
            mainFun()
            retView
        } else {
            TextView(activity).apply {
                setText(R.string.hello_blank_fragment)
            }
        }
    }

    private fun mainFun() {
        adapterFavoriteEventList = FavoriteEventListAdapter(this.context, mutableListFavoriteEvent) {
            startActivity<FavoriteEventDetailActivity>(
                "favoriteEvent" to it as Serializable
            )
        }
        recyclerViewFavoriteEvent.adapter = adapterFavoriteEventList
        presenterFavoriteEventList = FavoriteEventListPresenter(this, this.context)
        presenterFavoriteEventList.getFavoriteEvents()
        swipeRefresh.onRefresh {
            presenterFavoriteEventList.getFavoriteEvents()
        }
    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<FavoriteEvent>) {
        swipeRefresh.isRefreshing = false
        mutableListFavoriteEvent.clear()
        mutableListFavoriteEvent.addAll(data)
        adapterFavoriteEventList.notifyDataSetChanged()
    }

    inner class FavoriteEventListFragmentUI : AnkoComponent<ViewGroup?> {

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

                            recyclerViewFavoriteEvent = recyclerView {
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
