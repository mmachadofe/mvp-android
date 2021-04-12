package br.uol.ps.cards.domain.cards

import br.uol.ps.cards.RxImmediateSchedulerRule
import br.uol.ps.cards.contracts.CardsContract
import br.uol.ps.cards.models.Card
import br.uol.ps.cards.models.CardLayout
import br.uol.ps.cards.models.CardsResponseDto
import br.uol.ps.cards.presenter.CardsPresenter
import br.uol.ps.cards.repository.CardRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifySequence
import io.reactivex.rxjava3.core.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardsPresenterTest {
    @get:Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    // * Builds a new mock for specified class
    // * relaxed allows creation with no specific behaviour
    private val view = mockk<CardsContract.View>(relaxed = true)
    private val repository = mockk<CardRepository>(relaxed = true)

    private lateinit var presenter: CardsPresenter
    private lateinit var card: Card

    @Before
    fun setup() {
        card = Card(
            1000,
            "title",
            "description",
            "lastDigits",
            "cardBrand",
            CardLayout("bgColor", "titleColor")
        )
        presenter = CardsPresenter(view, repository)
    }

    @Test
    fun testGetCards() {
        every { repository.getCards() } returns Observable.just(CardsResponseDto(listOf()))
        presenter.getCards()

        view.run {
            verifySequence {
                showLoading()
                setListCardsVisible()
                showListCards(any())
                stopLoading()
            }
        }
    }

    @Test
    fun `test get cards when error result`() {
        every { repository.getCards() } returns Observable.error(Throwable())
        presenter.getCards()

        view.run {
            verifySequence {
                showLoading()
                stopLoading()
                showError()
            }
        }
    }

    @Test
    fun `test my fancy feature when open card detail is tapped`() {
        presenter.doOpenCardDetail(card)

        view.run {
            verifySequence {
                openCardDetail(card)
            }
        }
    }


    @Test
    fun `test card object`() {
        Assert.assertEquals(1000, card.id)
        Assert.assertEquals("title", card.title)
        Assert.assertEquals("description", card.description)
        Assert.assertEquals("lastDigits", card.lastDigits)
        Assert.assertEquals("cardBrand", card.cardBrand)
        Assert.assertEquals("bgColor", card.layoutAttrs.bgColor)
        Assert.assertEquals("titleColor", card.layoutAttrs.titleColor)
    }
}