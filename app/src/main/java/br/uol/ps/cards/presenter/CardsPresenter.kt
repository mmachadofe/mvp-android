package br.uol.ps.cards.presenter

import br.uol.ps.cards.contracts.CardsContract
import br.uol.ps.cards.models.Card
import br.uol.ps.cards.repository.CardRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CardsPresenter(
    var view: CardsContract.View?,
    private val cardRepository: CardRepository
) : CardsContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun getCards() {
        view?.showLoading()
        cardRepository
            .getCards()
            .doOnSubscribe { disposable }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), true)
            .doOnNext {
                //sucesso
                view?.showListCards(it.cards)
            }.doOnError {
                view?.showError()
            }.doOnComplete {
                view?.stopLoading()
            }
    }

    override fun doOpenCardDetail(card: Card) {
        view?.openCardDetail()
    }

    override fun dispose() {
        disposable.clear()
    }
}