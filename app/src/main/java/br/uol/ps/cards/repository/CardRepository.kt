package br.uol.ps.cards.repository

class CardRepository(
    private val api: CardsApi
) : CardsApi {

    override fun getCards() = api.getCards()

    override fun getCardDetail(walletId: String) = api.getCardDetail(walletId)
}