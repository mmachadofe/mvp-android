package br.uol.ps.cards.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.uol.ps.cards.R
import br.uol.ps.cards.contracts.CardsContract
import br.uol.ps.cards.models.Card
import br.uol.ps.cards.presenter.CardsPresenter

class CardsActivity : AppCompatActivity(), CardsContract.View {

    val presenter = CardsPresenter(this)

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
}