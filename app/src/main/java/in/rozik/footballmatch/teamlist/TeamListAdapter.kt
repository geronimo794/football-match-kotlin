package `in`.rozik.footballmatch.teamlist

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.models.FavoriteTeam
import `in`.rozik.footballmatch.system.models.Team
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamListAdapter(private val teams: List<Team>, private val listener: (Team) -> Unit) :
    RecyclerView.Adapter<TeamListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListHolder {
        return TeamListHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_team,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: TeamListHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.count()
}

class TeamListHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(team: Team, listener: (Team) -> Unit) {
        team.badge.let { Picasso.get().load(it).into(itemView.imageViewLogoTim) }
        itemView.textViewNamaTim.text = team.name
        itemView.linearLayoutItemTeam.onClick {
            listener(team)
        }
        itemView.textViewNamaTim.onClick {
            listener(team)
        }
        itemView.imageViewLogoTim.onClick {
            listener(team)
        }
    }

    fun bindFavoriteItem(team: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
        team.badge.let { Picasso.get().load(it).into(itemView.imageViewLogoTim) }
        itemView.textViewNamaTim.text = team.name
        itemView.linearLayoutItemTeam.onClick {
            listener(team)
        }
        itemView.textViewNamaTim.onClick {
            listener(team)
        }
        itemView.imageViewLogoTim.onClick {
            listener(team)
        }
    }

}
