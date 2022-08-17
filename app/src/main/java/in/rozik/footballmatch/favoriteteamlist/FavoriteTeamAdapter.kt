package `in`.rozik.footballmatch.favoriteteamlist

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.models.FavoriteTeam
import `in`.rozik.footballmatch.teamlist.TeamListHolder
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class FavoriteTeamAdapter(private val teams: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit) :
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
        holder.bindFavoriteItem(teams[position], listener)
    }

    override fun getItemCount(): Int = teams.count()
}