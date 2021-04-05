package br.uol.ps.cards.repository

import retrofit2.Retrofit
import retrofit2.create

class CardRepository(
    retrofit: Retrofit
) : CardsApi {

    private val api = retrofit.create<CardsApi>()

    override fun getCards() = api.getCards()

    override fun getCardDetail(walletId: String) = api.getCardDetail(walletId)
}