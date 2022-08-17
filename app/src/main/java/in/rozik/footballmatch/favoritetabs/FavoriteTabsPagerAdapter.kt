package `in`.rozik.footballmatch.favoritetabs

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.favoriteeventlist.FavoriteEventListFragment
import `in`.rozik.footballmatch.favoriteteamlist.FavoriteTeamListFragment
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class FavoriteTabsPagerAdapter(fm: FragmentManager?, private val context: Context) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> FavoriteEventListFragment()
            1 -> FavoriteTeamListFragment()
            else -> FavoriteEventListFragment()
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