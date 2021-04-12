package br.uol.ps.cards.repository

import br.uol.ps.cards.models.CardDetailDto
import br.uol.ps.cards.models.CardsResponseDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface CardsApi {

    @GET("/cards")
    fun getCards(): Observable<CardsResponseDto>

    @GET("/cards/{walletId}")
    fun getCardDetail(@Path("walletId") walletId: String): Observable<CardDetailDto>
}