package `in`.rozik.footballmatch.teamdescription

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.models.Team
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_fragment_team_info.view.*

class TeamDescriptionAdapter(private val context: Context?, private val team: Team) :
    RecyclerView.Adapter<TeamDescriptionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamDescriptionViewHolder {
        return TeamDescriptionViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_fragment_team_info,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: TeamDescriptionViewHolder, position: Int) {
        holder.bindItem(team, context)
    }

    override fun getItemCount(): Int = 1

}

class TeamDescriptionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(team: Team, context: Context?) {
        itemView.textViewFormedYear.text = team.formedYear
        itemView.textViewStadium.text = team.stadium
        itemView.textViewStadiumDescription.text = team.stadiumDescription

        itemView.textViewWebsite.text = team.website
        itemView.textViewDescriptionEN.text = team.descriptionEN
        team.stadiumThumb.let { Picasso.get().load(it).into(itemView.imageViewStadiumThumb) }
    }
}