package `in`.rozik.footballmatch.eventdetail

import `in`.rozik.footballmatch.R
import `in`.rozik.footballmatch.system.helpers.fullTimeFormatToDateWithDayGMT7
import `in`.rozik.footballmatch.system.helpers.fullTimeFormatToHourDayGMT7
import `in`.rozik.footballmatch.system.models.Event
import `in`.rozik.footballmatch.system.models.FavoriteEvent
import `in`.rozik.footballmatch.teamlogo.TeamLogoFragment
import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_event_detail.view.*

class EventDetailAdapter(
    private val context: Context,
    private val event: Event,
    private val fmManager: FragmentManager
) :
    RecyclerView.Adapter<EventDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDetailViewHolder {
        return EventDetailViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_event_detail,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventDetailViewHolder, position: Int) {
        holder.bindItemEvent(event, context, fmManager)

    }

    override fun getItemCount(): Int = 1
}

class EventDetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindItemEvent(event: Event, context: Context, supportFragmentManager: FragmentManager) {
        val strFullTime = event.heldDate + " " + event.heldTime

        itemView.txtvwDate.text = strFullTime.fullTimeFormatToDateWithDayGMT7()
        itemView.textViewHour.text = strFullTime.fullTimeFormatToHourDayGMT7()

        itemView.txtvwHomeScore.text = event.homeScore
        itemView.txtvwAwayScore.text = event.awayScore

        itemView.txtvwHomeTeam.text = event.homeTeam
        itemView.txtvwAwayTeam.text = event.awayTeam

        itemView.txtvwHomeShots.text = event.homeShot
        itemView.txtvwAwayShots.text = event.awayShot

        itemView.txtvwHomeGoalDetails.text = event.homeGoalDetails
        itemView.txtvwAwayGoalDetails.text = event.awayGoalDetails

        itemView.txtvwHomeLineupGoalkeeper.text = event.homeLineupGoalkeeper
        itemView.txtvwAwayLineupGoalkeeper.text = event.awayLineupGoalkeeper

        itemView.txtvwHomeLineupDefense.text = event.homeLineupDefense
        itemView.txtvwAwayLineupDefense.text = event.awayLineupDefense

        itemView.txtvwHomeLineupMidfield.text = event.homeLineupMidfield
        itemView.txtvwAwayLineupMidfield.text = event.awayLineupMidfield

        val bundleSendHome = Bundle()
        val bundleSendAway = Bundle()
        val homeLogo = TeamLogoFragment()
        val awayLogo = TeamLogoFragment()
        bundleSendHome.putString(context.getString(R.string.teamId), event.homeTeamId)
        homeLogo.arguments = bundleSendHome

        supportFragmentManager.beginTransaction().add(R.id.FrameLayoutHomeTeamLogo, homeLogo).commit()

        bundleSendAway.putString(context.getString(R.string.teamId), event.awayTeamId)
        awayLogo.arguments = bundleSendAway

        supportFragmentManager.beginTransaction().add(R.id.FrameLayoutAwayTeamLogo, awayLogo).commit()

    }

    fun bindItemFavoriteEvent(event: FavoriteEvent, context: Context, supportFragmentManager: FragmentManager) {
        val strFullTime = event.heldDate + " " + event.heldTime

        itemView.txtvwDate.text = strFullTime.fullTimeFormatToDateWithDayGMT7()
        itemView.textViewHour.text = strFullTime.fullTimeFormatToHourDayGMT7()

        itemView.txtvwHomeScore.text = event.homeScore
        itemView.txtvwAwayScore.text = event.awayScore

        itemView.txtvwHomeTeam.text = event.homeTeam
        itemView.txtvwAwayTeam.text = event.awayTeam

        itemView.txtvwHomeShots.text = event.homeShot
        itemView.txtvwAwayShots.text = event.awayShot

        itemView.txtvwHomeGoalDetails.text = event.homeGoalDetails
        itemView.txtvwAwayGoalDetails.text = event.awayGoalDetails

        itemView.txtvwHomeLineupGoalkeeper.text = event.homeLineupGoalkeeper
        itemView.txtvwAwayLineupGoalkeeper.text = event.awayLineupGoalkeeper

        itemView.txtvwHomeLineupDefense.text = event.homeLineupDefense
        itemView.txtvwAwayLineupDefense.text = event.awayLineupDefense

        itemView.txtvwHomeLineupMidfield.text = event.homeLineupMidfield
        itemView.txtvwAwayLineupMidfield.text = event.awayLineupMidfield

        val bundleSendHome = Bundle()
        val bundleSendAway = Bundle()
        val homeLogo = TeamLogoFragment()
        val awayLogo = TeamLogoFragment()
        bundleSendHome.putString(context.getString(R.string.teamId), event.homeTeamId)
        homeLogo.arguments = bundleSendHome

        supportFragmentManager.beginTransaction().add(R.id.FrameLayoutHomeTeamLogo, homeLogo).commit()

        bundleSendAway.putString(context.getString(R.string.teamId), event.awayTeamId)
        awayLogo.arguments = bundleSendAway

        supportFragmentManager.beginTransaction().add(R.id.FrameLayoutAwayTeamLogo, awayLogo).commit()

    }
}