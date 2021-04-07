package br.uol.ps.cards.presenter

import br.uol.ps.cards.contracts.DetailContract
import br.uol.ps.cards.repository.CardRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailPresenter(
    var view: DetailContract.View?,
    private val cardRepository: CardRepository
) : DetailContract.Presenter {

    private val disposable = CompositeDisposable()

    override fun getDetailCard(id: Int) {
        view?.showLoading()
        cardRepository
            .getCardDetail(id)
            .doOnSubscribe { disposable.add(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), true)
            .doOnNext {
                view?.setLimit(it.limit)
                view?.setBlockStatus(it.blockStatus)
            }.doOnError {
                view?.showError("Não foi possível completar sua requisição no momento, tenta novamente mais tarde.")
            }.doOnComplete {
                view?.stopLoading()
            }.subscribe()
    }

    override fun dispose() {
        disposable.clear()
    }
}