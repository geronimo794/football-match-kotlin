package `in`.rozik.footballmatch.eventbottomnavigation

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.R.id.itemLampau
import `in`.rozik.footballmatch.R.id.itemMendatang
import `in`.rozik.footballmatch.eventlist.EventListFragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.navigation_event_bottom.*

class EventBottomNavigationActivity : AppCompatActivity() {
    private lateinit var leagueId: String
    private lateinit var leagueName: String
    private lateinit var eventListFragmentLampau: EventListFragment
    private lateinit var eventListFragmentMendatang: EventListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_event_bottom)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mainFun()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun mainFun() {
        leagueId = intent.getStringExtra("leagueId")
        leagueName = intent.getStringExtra(getString(R.string.leagueName))
        title = "Daftar Laga $leagueName"

        loadAllFragment()
        bottomNavigationViewMenu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                itemLampau -> {
                    loadLigaLampau()
                }
                itemMendatang -> {
                    loadLigaMendatang()
                }
            }
            true
        }
        bottomNavigationViewMenu.selectedItemId = itemLampau
    }

    private fun loadLigaLampau() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayoutLiga, eventListFragmentLampau, EventListFragment::class.java.simpleName)
            .commit()

    }

    private fun loadLigaMendatang() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayoutLiga, eventListFragmentMendatang, EventListFragment::class.java.simpleName)
            .commit()
    }

    private fun loadAllFragment() {
        // Event mendatang
        eventListFragmentMendatang = EventListFragment()
        val bundleSend: Bundle = Bundle().apply {
            putString(getString(R.string.leagueId), leagueId)
            putString(getString(R.string.jenisLaga), getString(R.string.mendatang))
        }
        eventListFragmentMendatang.arguments = bundleSend

        // Event lampau
        eventListFragmentLampau = EventListFragment()
        val bundleLampauSend: Bundle = Bundle().apply {
            putString(getString(R.string.leagueId), leagueId)
            putString(getString(R.string.jenisLaga), getString(R.string.lampau))
        }
        eventListFragmentLampau.arguments = bundleLampauSend

    }

}
