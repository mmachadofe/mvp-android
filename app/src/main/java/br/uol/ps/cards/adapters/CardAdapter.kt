package br.uol.ps.cards.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.uol.ps.cards.R
import br.uol.ps.cards.models.Card
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_card.view.*
import java.lang.Exception

class CardAdapter(private val cards: List<Card>) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = cards.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.run {
            try{
                val card = cards[position]
                lastDigits.text = card.lastDigits
                lastDigits.setTextColor(Color.parseColor(card.layoutAttrs.titleColor))

                Picasso.get()
                    .load(card.cardBrand)
                    .into(imgBrand)

                tvTitle.text = card.title
                tvDescription.text = card.description

                cardView.setCardBackgroundColor(Color.parseColor(card.layoutAttrs.bgColor))

            }
            catch (e: Exception){

            }
        }
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}