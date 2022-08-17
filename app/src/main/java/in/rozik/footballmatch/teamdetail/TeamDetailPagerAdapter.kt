package `in`.rozik.footballmatch.teamdetail

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.playerlist.PlayerListFragment
import `in`.rozik.footballmatch.system.models.Team
import `in`.rozik.footballmatch.teamdescription.TeamDescriptionFragment
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class TeamDetailPagerAdapter(fm: FragmentManager?, private val team: Team, private val context: Context) :
    FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        val bundleSend = Bundle()
        val teamDescriptionFragment: TeamDescriptionFragment?
        val playerListFragment: PlayerListFragment?

        return when (position) {
            0 -> {
                teamDescriptionFragment = TeamDescriptionFragment()
                bundleSend.putSerializable(context.getString(R.string.team), team)
                teamDescriptionFragment.arguments = bundleSend
                teamDescriptionFragment
            }
            1 -> {
                playerListFragment = PlayerListFragment()
                bundleSend.putString(context.getString(R.string.teamId), team.teamId)
                playerListFragment.arguments = bundleSend
                playerListFragment

            }
            else -> {
                teamDescriptionFragment = TeamDescriptionFragment()
                bundleSend.putSerializable(context.getString(R.string.team), team)
                teamDescriptionFragment.arguments = bundleSend
                teamDescriptionFragment
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Informasi"
            1 -> "Data Pemain"
            else -> context.getString(R.string.laga_lampau)
        }
    }

}
