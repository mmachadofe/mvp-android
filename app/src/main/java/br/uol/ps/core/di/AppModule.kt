package br.uol.ps.core.di

import br.uol.ps.cards.contracts.CardsContract
import br.uol.ps.cards.contracts.DetailContract
import br.uol.ps.cards.presenter.CardsPresenter
import br.uol.ps.cards.presenter.DetailPresenter
import br.uol.ps.cards.repository.CardRepository
import br.uol.ps.cards.repository.CardsApi
import br.uol.ps.cards.view.CardsActivity
import br.uol.ps.cards.view.DetailActivity
import br.uol.ps.core.networkMiddleware.RetrofitConfiguration
import org.koin.dsl.module
import retrofit2.Retrofit

object AppModule {
    val instance = module {
        single {
            RetrofitConfiguration().getInstance()
        }
        single<CardsApi> {
            get<Retrofit>().create(CardsApi::class.java)
        }
        single {
            CardRepository(get())
        }

        scope<CardsActivity> {
            scoped<CardsContract.Presenter> { (view: CardsContract.View) ->
                CardsPresenter(view, get())
            }
        }

        scope<DetailActivity> {
            scoped<DetailContract.Presenter> { (view: DetailContract.View) ->
                DetailPresenter(view, get())
            }
        }
    }
}