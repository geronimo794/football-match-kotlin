package `in`.rozik.footballmatch.teamlogo

import `in`.rozik.footballmatch.ApiRepository
import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.helpers.invisible
import `in`.rozik.footballmatch.system.helpers.visible
import `in`.rozik.footballmatch.system.models.Team
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class TeamLogoFragment : Fragment(), TeamLogoView {
    private lateinit var progressBar: ProgressBar
    private lateinit var rclvwImage: RecyclerView
    private lateinit var adapterTeamLogo: TeamLogoAdapter
    private lateinit var objTeamLogoPresenter: TeamLogoPresenter

    private lateinit var teamId: String
    private var mutlsTeam: MutableList<Team> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (container != null) {
            val retView: View? = TeamLogoFragmentUI().createView(AnkoContext.create(container.context, container))
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

        teamId = arguments?.getString(getString(R.string.teamId)) ?: "1"
        Log.d("xxxTLF+teamId", teamId)

        objTeamLogoPresenter = TeamLogoPresenter(this, request, gson)
        objTeamLogoPresenter.getTeamView(teamId)


        adapterTeamLogo = TeamLogoAdapter(mutlsTeam)
        rclvwImage.adapter = adapterTeamLogo

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamLogo(data: List<Team>) {
        mutlsTeam.clear()
        mutlsTeam.addAll(data)
        Log.d("xxxTLF+mutlsTeam", mutlsTeam.toString())
        adapterTeamLogo.notifyDataSetChanged()
    }

    inner class TeamLogoFragmentUI : AnkoComponent<ViewGroup?> {

        override fun createView(ui: AnkoContext<ViewGroup?>): View {
            return with(ui) {

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    rclvwImage = recyclerView {
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

