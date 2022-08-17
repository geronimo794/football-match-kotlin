package `in`.rozik.footballmatch.teamdetail

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.R.drawable.ic_add_to_favorites
import `in`.rozik.footballmatch.R.drawable.ic_added_to_favorites
import `in`.rozik.footballmatch.system.helpers.database
import `in`.rozik.footballmatch.system.models.FavoriteTeam
import `in`.rozik.footballmatch.system.models.Team
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var team: Team
    private lateinit var teamDetailPresenter: TeamDetailPresenter
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_team_detail)
        title = "Detail Tim"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mainFun()
    }

    private fun mainFun() {
        val teamName = intent.getStringExtra(getString(R.string.teamName))

        team = intent.extras?.getSerializable(getString(R.string.team)) as Team
        teamDetailPresenter = TeamDetailPresenter(this, team)
        teamDetailPresenter.displayTeam()
        title = teamName

        Log.d("TDAteam", team.toString())
        // Check is favorited or not
        getFavoriteState()
    }

    private fun bindItem(team: Team) {
        team.badge.let { Picasso.get().load(it).into(imageViewTeamBadge) }

        // Pembuatan adapter untuk viewPagerInformasiTeam dan tabLayoutInformasiPemain
        val teamDetailPagerAdapter = TeamDetailPagerAdapter(supportFragmentManager, team, this)

        viewPagerInformasiTeam.adapter = teamDetailPagerAdapter
        tabLayoutInformasiPemain.setupWithViewPager(viewPagerInformasiTeam)
    }

    private fun getFavoriteState() {
        database.use {
            val result = select(FavoriteTeam.tableName)
                .whereArgs(
                    "(${FavoriteTeam.idTeam} = {idTeam})",
                    getString(R.string.idTeam) to team.teamId
                )
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavoriteMenuIcon() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    FavoriteTeam.tableName,
                    FavoriteTeam.idTeam to team.teamId,
                    FavoriteTeam.name to team.name,
                    FavoriteTeam.badge to team.badge,
                    FavoriteTeam.formedYear to team.formedYear,
                    FavoriteTeam.stadium to team.stadium,
                    FavoriteTeam.stadiumThumb to team.stadiumThumb,
                    FavoriteTeam.website to team.website,
                    FavoriteTeam.descriptionEN to team.descriptionEN
                )

            }
            linearLayoutTeamDetail.snackbar(getString(R.string.add_favorite)).show()
        } catch (e: SQLiteConstraintException) {
            linearLayoutTeamDetail.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    FavoriteTeam.tableName,
                    "(${FavoriteTeam.idTeam} = {idTeam})",
                    getString(R.string.idTeam) to team.teamId
                )
            }
            linearLayoutTeamDetail.snackbar(getString(R.string.remove_favorite)).show()
        } catch (e: SQLiteConstraintException) {
            linearLayoutTeamDetail.snackbar(e.localizedMessage).show()
        }
    }

    override fun showTeamDetail(data: Team) {
        team = data
        bindItem(team)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavoriteMenuIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.itemAddToFavorites -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavoriteMenuIcon()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}
