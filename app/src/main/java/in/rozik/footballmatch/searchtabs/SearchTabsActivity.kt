package `in`.rozik.footballmatch.searchtabs

import `in`.rozik.footballmatch.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.navigation_tab_activity.*

class SearchTabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_tab_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.pencarian_tim_laga)
        mainFun()
    }

    private fun mainFun() {
        viewPagerFavorite.adapter = SearchTabsPagerAdapter(supportFragmentManager, this)
        tabLayoutFavorite.setupWithViewPager(viewPagerFavorite)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
