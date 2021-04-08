package br.uol.ps.cards.contracts

import br.uol.ps.cards.models.Card

interface CardsContract {
    interface View {
        fun showListCards(cards: List<Card>)
        fun setListCardsVisible()
        fun openCardDetail(card: Card)
        fun showError()
        fun showLoading()
        fun stopLoading()
    }

    interface Presenter {
        fun getCards()
        fun doOpenCardDetail(card: Card)
        fun dispose()
    }
}