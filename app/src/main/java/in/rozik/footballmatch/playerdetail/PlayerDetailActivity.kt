package `in`.rozik.footballmatch.playerdetail

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.helpers.getPublicDateFormat
import `in`.rozik.footballmatch.system.helpers.getYearBetween
import `in`.rozik.footballmatch.system.models.Player
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_player_detail.*

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {

    private lateinit var playerDetailPresenter: PlayerDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_player_detail)
        title = "Detail Pemain"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mainFun()
    }

    private fun mainFun() {
        val player = intent.extras?.getSerializable(getString(R.string.player)) as Player
        title = player.name
        playerDetailPresenter = PlayerDetailPresenter(this)
        playerDetailPresenter.setPlayerDetail(player)
    }

    private fun bindItem(player: Player) {
        val weightHeight = player.weight.toString() + " / " + player.height
        val ageBorn = (player.born?.getYearBetween() ?: "") + " tahun / " + player.birthLocation
        val joinSince = "Join : " + (player.signed?.getPublicDateFormat() ?: "")

        player.imageThumb.let { Picasso.get().load(it).into(imageViewThumbPemain) }
        textViewGabungSejak.text = joinSince
        textViewWeightHeight.text = weightHeight
        textViewAgeBorn.text = ageBorn
        textViewPlayerDescription.text = player.descriptionEN
    }

    override fun showPlayerDetail(data: Player) {
        bindItem(data)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
