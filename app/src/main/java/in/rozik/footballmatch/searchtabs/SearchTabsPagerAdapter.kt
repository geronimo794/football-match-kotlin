package `in`.rozik.footballmatch.searchtabs

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.eventsearch.EventSearchFragment
import `in`.rozik.footballmatch.teamsearch.TeamSearchFragment
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class SearchTabsPagerAdapter(fm: FragmentManager?, private val context: Context) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> EventSearchFragment()
            1 -> TeamSearchFragment()
            else -> EventSearchFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.laga)
            1 -> context.getString(R.string.tim)
            else -> context.getString(R.string.laga)
        }
    }

}