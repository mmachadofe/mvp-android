package br.uol.ps.core.networkMiddleware

import br.uol.ps.cards.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
            baseUrl("https://private-f3be7e-pagbankbootcamp.apiary-mock.com")
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            client(httpClient.build())
        }.build()
    }
}