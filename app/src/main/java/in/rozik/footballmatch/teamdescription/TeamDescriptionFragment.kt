package `in`.rozik.footballmatch.teamdescription


import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.models.Team
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class TeamDescriptionFragment : Fragment(), TeamDescriptionView {

    private lateinit var recyclerViewDescription: RecyclerView
    private lateinit var teamDescriptionAdapter: TeamDescriptionAdapter
    private lateinit var teamDescriptionPresenter: TeamDescriptionPresenter
    private lateinit var team: Team

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (container != null) {
            val retView: View? =
                TeamDescriptionFragmentUI().createView(AnkoContext.create(container.context, container))
            mainFun()
            retView
        } else {
            TextView(activity).apply {
                setText(R.string.hello_blank_fragment)
            }
        }
    }

    override fun showTeamDescription(data: Team) {
        team = data
        teamDescriptionAdapter.notifyDataSetChanged()
    }

    private fun mainFun() {
        team = arguments?.getSerializable(getString(R.string.team)) as Team

        teamDescriptionAdapter = TeamDescriptionAdapter(this.context, team)
        recyclerViewDescription.adapter = teamDescriptionAdapter

        teamDescriptionPresenter = TeamDescriptionPresenter(this, team)
        teamDescriptionPresenter.displayTeam()

    }

    inner class TeamDescriptionFragmentUI : AnkoComponent<ViewGroup?> {

        override fun createView(ui: AnkoContext<ViewGroup?>): View {
            return with(ui) {

                verticalLayout {

                    recyclerViewDescription = recyclerView {
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                }

            }
        }
    }

}
