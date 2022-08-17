package `in`.rozik.footballmatch.eventtabs

import `in`.rozik.footballmatch.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.navigation_event_tab.*

class EventTabsActivity : AppCompatActivity() {

    private lateinit var eventPagerAdapter: EventPagerAdapter
    private var leagueId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_event_tab)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = getString(R.string.daftar_laga)
        mainFun()
    }

    private fun mainFun() {
        leagueId = intent.getStringExtra(getString(R.string.leagueId))

        eventPagerAdapter = EventPagerAdapter(supportFragmentManager, leagueId, this)
        vwpgrEvent.adapter = eventPagerAdapter
        tblytEvent.setupWithViewPager(vwpgrEvent)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
