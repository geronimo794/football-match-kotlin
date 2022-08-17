package `in`.rozik.footballmatch.playerlist

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.models.Player
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_player.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class PlayerListAdapter(
    private val listPlayer: List<Player>,
    private val context: Context?,
    private val listener: (Player) -> Unit
) : RecyclerView.Adapter<PlayerListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder {
        return PlayerListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_player,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listPlayer.size

    override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
        holder.bindItem(listPlayer[position], listener)
    }

}

class PlayerListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(player: Player, listener: (Player) -> Unit) {
        Picasso.get().load(player.imageCutout).into(itemView.imageViewFotoPemain)
        itemView.textViewNamaPemain.text = player.name
        itemView.textViewNamaPemain.onClick {
            listener(player)
        }
        itemView.imageViewFotoPemain.onClick {
            listener(player)
        }
        itemView.linearLayoutItemPlayer.onClick {
            listener(player)
        }
    }
}