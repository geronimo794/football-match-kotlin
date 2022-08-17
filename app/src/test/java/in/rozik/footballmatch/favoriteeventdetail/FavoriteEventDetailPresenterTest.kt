package `in`.rozik.footballmatch.favoriteeventdetail

import `in`.rozik.footballmatch.system.models.FavoriteEvent
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FavoriteEventDetailPresenterTest {

    @Mock
    private lateinit var view: FavoriteEventDetailView

    @Mock
    private lateinit var event: FavoriteEvent

    private lateinit var presenter: FavoriteEventDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = FavoriteEventDetailPresenter(view, event)
    }

    @Test
    fun displayEvent() {
        presenter.displayEvent()
        Mockito.verify(view).showEventDetail(event)
    }
}