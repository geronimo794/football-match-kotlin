package `in`.rozik.footballmatch

import ViewActionConstrainLayout
import `in`.rozik.footballmatch.R.id.*
import `in`.rozik.footballmatch.leaguelist.LeagueListActivity
import `in`.rozik.footballmatch.system.helpers.EspressoIdle
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TestByTask {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(LeagueListActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdle.getIdlingResource())
    }

    @Test
    fun detailPertandingan() {
        onView(withId(recyclerViewLiga)).check(matches(isDisplayed()))
        onView(withId(recyclerViewLiga)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(recyclerViewLiga)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                9,
                click(blockPantauLaga, 1)
            )
        )
        onView(withId(itemLampau)).perform(click())
        onView(withId(recyclerViewListEvent)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        Thread.sleep(5000)

    }

    @Test
    fun daftarPertandinganYangAkanDatang() {
        onView(withId(recyclerViewLiga)).check(matches(isDisplayed()))
        onView(withId(recyclerViewLiga)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(recyclerViewLiga)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                9,
                click(blockPantauLaga,1)
            )
        )
        onView(withId(itemMendatang)).perform(click())
        Thread.sleep(5000)
    }

    @Test
    fun daftarPertandinganYangSudahSelesai() {
        onView(withId(recyclerViewLiga)).check(matches(isDisplayed()))
        onView(withId(recyclerViewLiga)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(recyclerViewLiga)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                9,
                click(blockPantauLaga,1)
            )
        )
        onView(withId(itemLampau)).perform(click())
        Thread.sleep(5000)
    }



// Dapat membuat test gagal karna akan membuka activity lain
//    @Test
//    fun menambahkanReminderUntukPertandinganYangAkanDatangKeDalamCalendarEvents() {
//        onView(withId(recyclerViewLiga)).check(matches(isDisplayed()))
//        onView(withId(recyclerViewLiga)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
//        onView(withId(recyclerViewLiga)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                9,
//                ViewActionConstrainLayout.clickChildViewWithId(blockPantauLaga)
//            )
//        )
//        onView(withId(itemMendatang)).perform(click())
//        onView(withId(recyclerViewListEvent)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                1,
//                ViewActionImageView.clickChildViewWithId(setReminder)
//            )
//        )
//        Thread.sleep(5000)
//    }

    @Test
    fun daftarTim() {
        onView(withId(recyclerViewLiga)).check(matches(isDisplayed()))
        onView(withId(recyclerViewLiga)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(recyclerViewLiga)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                9,
                ViewActionConstrainLayout.clickChildViewWithId(blockLihatTim)
            )
        )
        Thread.sleep(5000)
    }



    @Test
    fun detailTimDilengkapiDenganDaftarPemainDetailParaPemain() {
        onView(withId(recyclerViewLiga)).check(matches(isDisplayed()))
        onView(withId(recyclerViewLiga)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(recyclerViewLiga)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                9,
                ViewActionConstrainLayout.clickChildViewWithId(blockLihatTim)
            )
        )
        onView(withId(recyclerViewTeam)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        isCompletelyDisplayed()
        onView(withId(viewPagerInformasiTeam)).perform(swipeLeft())

        onView(withId(recyclerViewPlayer)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                ViewActionImageView.clickChildViewWithId(imageViewFotoPemain)
            )
        )
        Thread.sleep(5000)
    }

    @Test
    fun daftarTimFavorite() {
        onView(withId(itemFavoriteList)).check(matches(isDisplayed()))
        onView(withId(itemFavoriteList)).perform(click())
        onView(withId(viewPagerFavorite)).perform(swipeLeft())
        Thread.sleep(5000)

    }

    @Test
    fun daftarPertandinganFavorite() {
        onView(withId(itemFavoriteList)).check(matches(isDisplayed()))
        onView(withId(itemFavoriteList)).perform(click())
        Thread.sleep(5000)
    }
    /**
     * Instrument test yang bisa gagal karena device bisa tidak mendukung otomatis ngetik
     */

//    @Test
//    fun pencarianTim() {
//        onView(withId(itemSearchData)).check(matches(isDisplayed()))
//        onView(withId(itemSearchData)).perform(click())
//        isCompletelyDisplayed()
//        onView(withId(viewPagerFavorite)).perform(swipeLeft());
//        onView(withId(searchViewTim)).perform(click())
//        onView(withId(searchViewTim)).perform(typeText("Arsenal"))
//        onView(withId(searchViewTim)).perform(pressKey(KeyEvent.KEYCODE_ENTER))
//
//        Thread.sleep(5000)
//    }
//    @Test
//    fun pencarianPertandingan() {
//        onView(withId(itemSearchData)).check(matches(isDisplayed()))
//        onView(withId(itemSearchData)).perform(click())
//        onView(withId(searchViewPertandingan)).perform(click())
//            .perform(typeText("Arsenal"))
//            .perform(pressKey(KeyEvent.KEYCODE_ENTER) )
//
//        Thread.sleep(5000)
//    }

    /**
     * RIP Previous Insrument test
     *
     */
//    @Test
//    fun testWithoutThreadSleep() {
//
//        // Buka Activity utama
//        // Klik liga urutan kedua
//        Log.d("espressoTest", "1.Klik liga urutan kedua")
//        onView(withId(recyclerViewLiga)).check(matches(isDisplayed()))
//        onView(withId(recyclerViewLiga)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                2,
//                click()
//            )
//        )
//        // Tampilkan halaman daftar laga lampau berdasarkan liga yang dipilih
//        // Scroll ke bawah 10
//        Log.d("espressoTest", "2.Scroll ke bawah 10")
//        onView(withId(recyclerViewListEvent)).check(matches(isDisplayed()))
//
//        onView(withId(recyclerViewListEvent)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
//
//
//        // Klik RecyclerView position 10
//        Log.d("espressoTest", "3.Klik RecyclerView position 10")
//        onView(withId(recyclerViewListEvent)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                10,
//                click()
//            )
//        )
//
//        // Favoritkan
//        Log.d("espressoTest", "4.Favoritkan")
//        onView(withId(itemAddToFavorites)).check(matches(isDisplayed()))
//
//        onView(withId(itemAddToFavorites)).perform(click())
//        onView(withId(itemAddToFavorites)).check(matches(isDisplayed()))
//
//
//        // Kembali ke halaman daftar laga lampau dari halaman detail laga
//        Log.d("espressoTest", "5.Kembali ke halaman daftar laga lampau dari halaman detail laga")
//        pressBack()
//
//        // Klik tab halaman daftar laga mendatang
//        Log.d("espressoTest", "6.Klik tab halaman daftar laga mendatang")
//        onView(withId(itemMendatang)).check(matches(isDisplayed()))
//        onView(withId(itemMendatang)).perform(click())
//
//        // Scroll ke bawah 5
//        Log.d("espressoTest", "7.Scroll ke bawah 5")
//        onView(withId(recyclerViewListEvent)).check(matches(isDisplayed()))
//        onView(withId(recyclerViewListEvent)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
//
//
//        // Klik RecyclerView posisi 5
//        Log.d("espressoTest", "8.Klik RecyclerView posisi 5")
//        onView(withId(recyclerViewListEvent)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                5,
//                click()
//            )
//        )
//
//        // Favoritkan
//        Log.d("espressoTest", "9.Favoritkan")
//        onView(withId(itemAddToFavorites)).check(matches(isDisplayed()))
//        onView(withId(itemAddToFavorites)).perform(click())
//        onView(withId(itemAddToFavorites)).check(matches(isDisplayed()))
//
//
//        // Kembali ke daftar laga mendatang
//        Log.d("espressoTest", "10.Kembali ke daftar laga mendatang")
//        pressBack()
//
//
//        // Kembali ke halaman beranda
//        Log.d("espressoTest", "11.Kembali ke halaman beranda")
//        pressBack()
//
//        // Klik menu daftar laga favorit
//        Log.d("espressoTest", "12.Klik menu daftar laga favorit")
//        onView(withId(itemFavoriteList)).check(matches(isDisplayed()))
//        onView(withId(itemFavoriteList)).perform(click())
//
//        // Klik favorite index posisi 1
//        Log.d("espressoTest", "13.Klik favorite index posisi 1")
//        onView(withId(recyclerViewFavoriteEvent)).check(matches(isDisplayed()))
//        onView(withId(recyclerViewFavoriteEvent)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                1,
//                click()
//            )
//        )
//
//        // Hilangkan dari favorite
//        Log.d("espressoTest", "14.Hilangkan dari favorite")
//        onView(withId(itemAddToFavorites)).check(matches(isDisplayed()))
//        onView(withId(itemAddToFavorites)).perform(click())
//
//        // Kembali ke daftar favorite
//        Log.d("espressoTest", "15.Kembali ke daftar favorite")
//        pressBack()
//        onView(withId(recyclerViewFavoriteEvent)).check(matches(isDisplayed()))
//
//
//        // Klik favorite index posisi 0
//        Log.d("espressoTest", "16.Klik favorite index posisi 0")
//        onView(withId(recyclerViewFavoriteEvent)).check(matches(isDisplayed()))
//
//        onView(withId(recyclerViewFavoriteEvent)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                0,
//                click()
//            )
//        )
//
//        // Hilangkan dari favorite
//        Log.d("espressoTest", "17.Hilangkan dari favorite")
//        onView(withId(itemAddToFavorites)).check(matches(isDisplayed()))
//        onView(withId(itemAddToFavorites)).perform(click())
//
//
//        // Kembali ke daftar favorite kosong
//        Log.d("espressoTest", "18.Kembali ke daftar favorite yang kosong")
//        pressBack()
//
//
//        // Kembali ke beranda utama
//        Log.d("espressoTest", "19.Kembali ke beranda utama")
//        pressBack()
//        onView(withId(recyclerViewLiga)).check(matches(isDisplayed()))
//    }
//    @Test
//    fun testWithThreadSleep() {
//
//        // Buka Activity utama
//        // Klik liga urutan kedua
//        Log.d("espressoTest", "1.Klik liga urutan kedua")
//        onView(withId(recyclerViewLiga)).check(matches(isDisplayed()))
//        onView(withId(recyclerViewLiga)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                2,
//                click()
//            )
//        )
//        // Tampilkan halaman daftar laga lampau berdasarkan liga yang dipilih
//        // Scroll ke bawah 10
//        Log.d("espressoTest", "2.Scroll ke bawah 10")
//        onView(withId(recyclerViewListEvent)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//        onView(withId(recyclerViewListEvent)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
//        Thread.sleep(1000)
//
//        // Klik RecyclerView position 10
//        Log.d("espressoTest", "3.Klik RecyclerView position 10")
//        onView(withId(recyclerViewListEvent)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                10,
//                click()
//            )
//        )
//
//        // Favoritkan
//        Log.d("espressoTest", "4.Favoritkan")
//        onView(withId(itemAddToFavorites)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//        onView(withId(itemAddToFavorites)).perform(click())
//        onView(withId(itemAddToFavorites)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//
//        // Kembali ke halaman daftar laga lampau dari halaman detail laga
//        Log.d("espressoTest", "5.Kembali ke halaman daftar laga lampau dari halaman detail laga")
//        pressBack()
//
//        // Klik tab halaman daftar laga mendatang
//        Log.d("espressoTest", "6.Klik tab halaman daftar laga mendatang")
//        onView(withId(itemMendatang)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//        onView(withId(itemMendatang)).perform(click())
//
//        // Scroll ke bawah 5
//        Log.d("espressoTest", "7.Scroll ke bawah 5")
//        onView(withId(recyclerViewListEvent)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//        onView(withId(recyclerViewListEvent)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
//        Thread.sleep(1000)
//
//        // Klik RecyclerView posisi 5
//        Log.d("espressoTest", "8.Klik RecyclerView posisi 5")
//        onView(withId(recyclerViewListEvent)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                5,
//                click()
//            )
//        )
//
//        // Favoritkan
//        Log.d("espressoTest", "9.Favoritkan")
//        onView(withId(itemAddToFavorites)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//        onView(withId(itemAddToFavorites)).perform(click())
//        onView(withId(itemAddToFavorites)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//
//        // Kembali ke daftar laga mendatang
//        Log.d("espressoTest", "10.Kembali ke daftar laga mendatang")
//        pressBack()
//        Thread.sleep(500)
//
//        // Kembali ke halaman beranda
//        Log.d("espressoTest", "11.Kembali ke halaman beranda")
//        pressBack()
//
//        // Klik menu daftar laga favorit
//        Log.d("espressoTest", "12.Klik menu daftar laga favorit")
//        onView(withId(itemFavoriteList)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//        onView(withId(itemFavoriteList)).perform(click())
//
//        // Klik favorite index posisi 1
//        Log.d("espressoTest", "13.Klik favorite index posisi 1")
//        onView(withId(recyclerViewFavoriteEvent)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//        onView(withId(recyclerViewFavoriteEvent)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                1,
//                click()
//            )
//        )
//
//        // Hilangkan dari favorite
//        Log.d("espressoTest", "14.Hilangkan dari favorite")
//        onView(withId(itemAddToFavorites)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//        onView(withId(itemAddToFavorites)).perform(click())
//
//        // Kembali ke daftar favorite
//        Log.d("espressoTest", "15.Kembali ke daftar favorite")
//        pressBack()
//        onView(withId(recyclerViewFavoriteEvent)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//
//        // Klik favorite index posisi 0
//        Log.d("espressoTest", "16.Klik favorite index posisi 0")
//        onView(withId(recyclerViewFavoriteEvent)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//        onView(withId(recyclerViewFavoriteEvent)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                0,
//                click()
//            )
//        )
//
//        // Hilangkan dari favorite
//        Log.d("espressoTest", "17.Hilangkan dari favorite")
//        onView(withId(itemAddToFavorites)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//        onView(withId(itemAddToFavorites)).perform(click())
//
//
//        // Kembali ke daftar favorite kosong
//        Log.d("espressoTest", "18.Kembali ke daftar favorite yang kosong")
//        pressBack()
//        Thread.sleep(1000)
//
//        // Kembali ke beranda utama
//        Log.d("espressoTest", "19.Kembali ke beranda utama")
//        pressBack()
//        onView(withId(recyclerViewLiga)).check(matches(isDisplayed()))
//        Thread.sleep(1000)
//    }
}