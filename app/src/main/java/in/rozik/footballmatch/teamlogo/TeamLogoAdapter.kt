package `in`.rozik.footballmatch.teamlogo

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.models.Team
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class TeamLogoAdapter(private val teams: List<Team>) : RecyclerView.Adapter<TeamLogoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamLogoHolder {
        return TeamLogoHolder(
            TeamLogoUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }


    override fun onBindViewHolder(holder: TeamLogoHolder, position: Int) {
        holder.bindItem(teams[position])
    }

    override fun getItemCount(): Int = teams.count()
}

class TeamLogoHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imgTeamLogo: ImageView = view.find(R.id.img_team_logo)
    fun bindItem(team: Team) {
        team.badge.let { Picasso.get().load(it).into(imgTeamLogo) }
        Log.d("xxxTLA+team.badge", team.badge)

    }
}

class TeamLogoUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            verticalLayout {
                imageView {
                    id = R.id.img_team_logo
                }.lparams {
                    height = dip(120)
                    width = matchParent
                }
            }
        }
    }

}
