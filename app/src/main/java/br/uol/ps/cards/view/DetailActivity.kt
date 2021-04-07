package br.uol.ps.cards.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.uol.ps.cards.R
import br.uol.ps.cards.contracts.DetailContract
import br.uol.ps.cards.models.Card
import br.uol.ps.cards.presenter.CardsPresenter
import br.uol.ps.cards.presenter.DetailPresenter
import br.uol.ps.cards.repository.CardRepository
import br.uol.ps.core.networkMiddleware.RetrofitConfiguration
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.row_card.view.*

class DetailActivity : AppCompatActivity(), DetailContract.View {

    //todo aula com o julio sobre injeção de dependência
    private val presenter = DetailPresenter(this, CardRepository(RetrofitConfiguration().getInstance()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if(intent.hasExtra(CARD)){
            val card = intent.getParcelableExtra<Card>(CARD)
            card?.also {
                tv_card.text = CARD_NUMBER.format(it.lastDigits)
                tv_card.setTextColor(Color.parseColor(it.layoutAttrs.titleColor))

                tv_situacao.text = it.description

                Picasso.get().load(it.cardBrand).into(imgBrand)

                cardView2.setCardBackgroundColor(Color.parseColor(it.layoutAttrs.bgColor))

                title = it.title

                presenter.getDetailCard(it.id)
            }
        }
    }

    override fun setLimit(limit: String) {
        tv_saldo.text = limit
    }

    override fun setBlockStatus(status: Boolean) {
        swtBlockCard.isChecked = status
    }

    override fun showError(message: String) {
        //TODO("Not yet implemented")
    }

    override fun showLoading() {
        //TODO("Not yet implemented")
    }

    override fun stopLoading() {
        //TODO("Not yet implemented")
    }

    companion object {

        const val CARD = "card"
        val CARD_NUMBER = "**** **** **** %s"

        fun newIntent(context: Context, card: Card) = Intent(context, DetailActivity::class.java).apply {
            putExtra(CARD, card)
        }
    }
}