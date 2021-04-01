package br.uol.ps.cards.presenter

import br.uol.ps.cards.contracts.CardsContract
import br.uol.ps.cards.models.Card

class CardsPresenter(
    var view: CardsContract.View?
) : CardsContract.Presenter {

    override fun getCards() {
        view?.showListCards(arrayListOf())
    }

    override fun doOpenCardDetail(card: Card) {
        view?.openCardDetail()
    }
}