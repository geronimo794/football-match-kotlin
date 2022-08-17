package `in`.rozik.footballmatch.favoritetabs

import `in`.rozik.footballmatch.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.navigation_tab_activity.*

class FavoriteTabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_tab_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.data_favorit_anda)
        mainFun()
    }

    private fun mainFun() {
        viewPagerFavorite.adapter = FavoriteTabsPagerAdapter(supportFragmentManager, this)
        tabLayoutFavorite.setupWithViewPager(viewPagerFavorite)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
