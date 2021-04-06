package br.uol.ps.cards.contracts

import br.uol.ps.cards.models.Card

interface CardsContract {
    interface View {
        fun showListCards(cards: List<Card>)
        fun openCardDetail()
        fun showError(message: String)
        fun showLoading()
        fun stopLoading()
    }

    interface Presenter {
        fun getCards()
        fun doOpenCardDetail(card: Card)
        fun dispose()
    }
}