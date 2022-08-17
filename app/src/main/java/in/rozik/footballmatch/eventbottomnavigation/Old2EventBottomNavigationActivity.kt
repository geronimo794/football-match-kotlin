package `in`.rozik.footballmatch.eventbottomnavigation

import `in`.rozik.footballmatch.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class Old2EventBottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_event_bottom)
        title = getString(R.string.footbal_apps)
//        mainFun()
    }
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.search_menu, menu)
//        // Associate searchable configuration with the SearchView
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        (menu.findItem(R.id.search).actionView as SearchView).apply {
//            setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        }
//        this.mainMenu = menu
//        return true
//    }
//
//
//    private fun mainFun(){
//
//        val bedMenuItem = this.mainMenu?.findItem(R.id.search)
//        bottomNavigationViewMenu.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                itemLaga -> {
//                    bedMenuItem?.setVisible(false)
//                    toast("Item laga")
//                }
//                itemTim -> {
//                    bedMenuItem?.setVisible(false)
//                    toast("item Tim")
//
//                }
//                itemDaftarFavorit -> {
//                    bedMenuItem?.setVisible(false)
//                    toast("Item Daftar Fav")
//                }
//            }
//            true
//        }
//        bottomNavigationViewMenu.selectedItemId = itemLaga
//
//    }

}
