package br.uol.ps.cards.presenter

import br.uol.ps.cards.contracts.CardsContract
import br.uol.ps.cards.models.Card
import br.uol.ps.cards.repository.CardRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CardsPresenter(
    private var view: CardsContract.View?,
    private val cardRepository: CardRepository
) : CardsContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun getCards() {
        view?.showLoading()
        cardRepository
            .getCards()
            .doOnSubscribe { disposable.add(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), true)
            .doOnTerminate {
                view?.stopLoading()
            }.subscribe({
                view?.setListCardsVisible()
                view?.showListCards(it.cards)
            }, {
                view?.showError()
            })
    }

    override fun doOpenCardDetail(card: Card) {
        view?.openCardDetail(card)
    }

    override fun dispose() {
        disposable.clear()
    }
}