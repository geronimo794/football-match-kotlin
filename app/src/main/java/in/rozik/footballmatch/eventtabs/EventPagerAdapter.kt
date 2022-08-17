package `in`.rozik.footballmatch.eventtabs

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.eventlist.EventListFragment
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class EventPagerAdapter(fm: FragmentManager?, inpLeagueId: String, private val context: Context) :
    FragmentStatePagerAdapter(fm) {
    private val leagueId: String = inpLeagueId

    override fun getItem(position: Int): Fragment {
        val pickedFragment = EventListFragment()
        val bundleSend = Bundle()

        bundleSend.putString(context.getString(R.string.leagueId), leagueId)

        when (position) {
            0 -> bundleSend.putString(context.getString(R.string.jenisLaga), context.getString(R.string.lampau))
            1 -> bundleSend.putString(context.getString(R.string.jenisLaga), context.getString(R.string.mendatang))
            else -> bundleSend.putString(context.getString(R.string.jenisLaga), context.getString(R.string.lampau))
        }

        pickedFragment.arguments = bundleSend
        return pickedFragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.laga_lampau)
            1 -> context.getString(R.string.laga_mendatang)
            else -> context.getString(R.string.laga_lampau)
        }
    }

}