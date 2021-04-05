package br.uol.ps.core.networkMiddleware

import br.uol.ps.cards.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class RetrofitConfiguration {

    fun getInstance(): Retrofit {

        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) logging.level =
            HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val httpClient = OkHttpClient
            .Builder().apply {
                readTimeout(60, TimeUnit.SECONDS)
                connectTimeout(60, TimeUnit.SECONDS)
                addInterceptor(logging)
            }

        return Retrofit.Builder().apply {
            baseUrl("https://private-52207a-pagbankbootcamp.apiary-mock.com")
            client(httpClient.build())
        }.build()
    }
}