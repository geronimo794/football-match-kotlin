package `in`.rozik.footballmatch.leaguelist

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.models.League
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainAdapter(
    private val leagues: List<League>,
    private val listener: (League) -> Unit,
    private val listenerTim: (League) -> Unit
) :
    RecyclerView.Adapter<LeagueListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueListViewHolder {
        return LeagueListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_league,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LeagueListViewHolder, position: Int) {
        holder.bindItem(leagues[position], listener, listenerTim)
    }

    override fun getItemCount(): Int = leagues.size
}
//class LeagueUI : AnkoComponent<ViewGroup> {
//    private lateinit var textViewLeagueName : TextView
//    override fun createView(ui: AnkoContext<ViewGroup>): View {
//        return with(ui) {
//            linearLayout {
//                lparams(width = matchParent, height = wrapContent)
//                padding = dip(16)
//                orientation = LinearLayout.HORIZONTAL
//
//                imageView {
//                    id = R.id.league_badge
//                }.lparams{
//                    height = dip(50)
//                    width = dip(50)
//                }
//                textViewLeagueName = textView {
//                    id = R.id.league_name
//                    textSize = 16f
//                }.lparams{
//                    margin = dip(15)
//                }
//                textViewLeagueName.onClick {
//                    toast(textViewLeagueName.text)
//                }
//            }
//        }
//    }
//
//}

class LeagueListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imgLeagueBadge: ImageView = view.find(R.id.league_badge)
    private val txtLeagueName: TextView = view.find(R.id.league_name)
    private val blockPantauLaga: ConstraintLayout = view.find(R.id.blockPantauLaga)
    private val blockLihatTim: ConstraintLayout = view.find(R.id.blockLihatTim)

    fun bindItem(leagues: League, listener: (League) -> Unit, listenerTim: (League) -> Unit) {
        Picasso.get().load(leagues.leagueBadge).into(imgLeagueBadge)
        txtLeagueName.text = leagues.leagueName
        blockPantauLaga.onClick {
            listener(leagues)
        }
        blockLihatTim.onClick {
            listenerTim(leagues)
        }
    }
}
