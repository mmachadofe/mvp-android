package br.uol.ps.cards.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.uol.ps.cards.R
import br.uol.ps.cards.contracts.CardsContract
import br.uol.ps.cards.models.Card
import br.uol.ps.cards.presenter.CardsPresenter
import br.uol.ps.cards.repository.CardRepository
import br.uol.ps.core.networkMiddleware.RetrofitConfiguration

class CardsActivity : AppCompatActivity(), CardsContract.View {

    //todo aula com o julio sobre injeção de dependncia
    val presenter = CardsPresenter(this, CardRepository(RetrofitConfiguration().getInstance()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)

        presenter.getCards()
    }

    override fun showListCards(cards: List<Card>) {
    }

    override fun openCardDetail() {
        //TODO("Not yet implemented")
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}