package br.uol.ps.cards.domain.cards

import br.uol.ps.cards.RxImmediateSchedulerRule
import br.uol.ps.cards.contracts.DetailContract
import br.uol.ps.cards.models.CardDetailDto
import br.uol.ps.cards.presenter.DetailPresenter
import br.uol.ps.cards.repository.CardRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class DetailPresenterTest {
    @get:Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var presenter: DetailPresenter

    private val view = mockk<DetailContract.View>(relaxed = true)
    private val repository = mockk<CardRepository>(relaxed = true)
    private val id = "2"

    @Before
    fun setup() {
        presenter = DetailPresenter(view, repository)
    }

    @Test
    fun `test get card detail when success result`() {
        every { repository.getCardDetail(id) } returns Observable.just(CardDetailDto(limit = "1000", blockStatus = false))
        presenter.getDetailCard(id)
        view.run {
            verifySequence {
                showLoading()
                setLimit("1000")
                setBlockStatus(false)
                stopLoading()
            }
        }
    }

    @Test
    fun `test get card detail when error result`(){
        every { repository.getCardDetail(id) } returns Observable.error(Throwable())
        presenter.getDetailCard(id)
        view.run {
            verifySequence {
                showLoading()
                stopLoading()
                showError()
            }
        }
    }
}