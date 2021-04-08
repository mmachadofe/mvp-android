package br.uol.ps.cards.view

import android.os.Bundle
import android.widget.Button
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
    private var mErrorDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)
        presenter.getCards()
    }

    override fun showListCards(cards: List<Card>) {
        rvCards.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val cardAdapter = CardAdapter(cards)
            cardAdapter.setListener {
                presenter.doOpenCardDetail(it)
            }

            adapter = cardAdapter
        }
    }

    override fun openCardDetail(card: Card) {
        startActivity(
            DetailActivity.newIntent(applicationContext, card)
        )
    }

    override fun showError() {
        val errorDialogView = layoutInflater.inflate(R.layout.error_dialog, null)
        val btn = errorDialogView.findViewById<Button>(R.id.feedbackButton)

        btn.setOnClickListener(){
            this.stopErrorDialog()
        }

        mErrorDialog = AlertDialog.Builder(this)
            .setView(errorDialogView)
            .setCancelable(false)
            .create()

        mErrorDialog?.show()
    }

    private fun stopErrorDialog() {
        mErrorDialog?.dismiss()
    }

    override fun showLoading() {
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)

        mAlertDialog = AlertDialog.Builder(this)
                .setView(dialogView)
                .setCancelable(false)
                .create()

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