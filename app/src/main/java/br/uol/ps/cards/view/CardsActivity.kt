package br.uol.ps.cards.view

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

    //todo aula com o julio sobre injeção de dependência
    private val presenter = CardsPresenter(this, CardRepository(RetrofitConfiguration().getInstance()))
    private var mAlertDialog: AlertDialog? = null

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

    override fun showError(message: String) {
        // todo Mostar uma Dialog com a mensagem
        // TITLE: Falha na requisição
        // MESSAG: Não foi possível completar sua requisição no momento, tenta novamente mais tarde.
        // BOTÃO: Botão OK

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun buildDialog(): AlertDialog {
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        val titleProgress = dialogView.findViewById<TextView>(R.id.tvTitleProgress)
        val messageProgress = dialogView.findViewById<TextView>(R.id.tvMessageProgress)
        titleProgress.text = getString(R.string.progress_title)
        messageProgress.text= getString(R.string.progress_message)

        return AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create()
    }

    override fun showLoading() {
        mAlertDialog = buildDialog()
        mAlertDialog?.show()
    }

    override fun stopLoading() {
        mAlertDialog?.dismiss()
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}