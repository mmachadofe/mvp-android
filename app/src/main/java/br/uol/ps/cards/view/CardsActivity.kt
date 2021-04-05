package br.uol.ps.cards.view

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.uol.ps.cards.R
import br.uol.ps.cards.adapters.CardAdapter
import br.uol.ps.cards.contracts.CardsContract
import br.uol.ps.cards.models.Card
import br.uol.ps.cards.presenter.CardsPresenter
import br.uol.ps.cards.repository.CardRepository
import br.uol.ps.core.networkMiddleware.RetrofitConfiguration
import kotlinx.android.synthetic.main.activity_cards.*

class CardsActivity : AppCompatActivity(), CardsContract.View {

    //todo aula com o julio sobre injeção de dependncia
    private val presenter = CardsPresenter(this, CardRepository(RetrofitConfiguration().getInstance()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)

        presenter.getCards()
    }

    override fun showListCards(cards: List<Card>) {
        rvCards.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CardAdapter(cards)
        }
    }

    override fun openCardDetail() {
        //TODO("Not yet implemented")
        //Paulo Afonso
    }

    override fun showError() {
        //TODO("Not yet implemented"
        //Mostar uma Dialog  com a mensagem
        // TITLE: Falha na requisição
        // MESSAG: Não foi possível completar sua requisição no momento, tenta novamente mais tarde.
        // BOTÃO: Botão OK
    }

    override fun showLoading() {
        //TODO("Not yet implemented")
        //Utilizar ProgressDialog - iniciar
    }

    override fun stopLoading() {
        //TODO("Not yet implemented")
        //Utilizar ProgressDialog - parar
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}