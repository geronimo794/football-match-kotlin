package `in`.rozik.footballmatch.leaguelist

import `in`.rozik.footballmatch.ApiRepository
import `in`.rozik.footballmatch.R.string.league_country
import `in`.rozik.footballmatch.R.string.league_sport
import `in`.rozik.footballmatch.TheSportDBApi
import `in`.rozik.footballmatch.system.models.League
import `in`.rozik.footballmatch.system.models.LeagueResponse
import android.content.res.Resources
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class LeagueListPresenterTest {
    @Mock
    private lateinit var view: LeagueListView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var res: Resources

    private lateinit var presenter: LeagueListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LeagueListPresenter(view, apiRepository, gson)
    }

    @Test
    fun getTeamList() {
        val leagues: MutableList<League> = mutableListOf()
        val response = LeagueResponse(leagues)
        GlobalScope.launch {

            `when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(
                            TheSportDBApi.getLeagues(
                                res.getString(league_country),
                                res.getString(league_sport)
                            )
                        ).await(),
                    LeagueResponse::class.java
                )
            ).thenReturn(response)

            presenter.getLeagueList(res.getString(league_country), res.getString(league_sport))

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showLeagueList(leagues)
            Mockito.verify(view).hideLoading()
        }
    }
}