package com.example.samplelistapp.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {
    var api: BinanceApi
        private set
    private var retrofit: Retrofit? = null

    companion object {
        val INSTANCE: ApiClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { ApiClient() }

        private const val BASE_URL = "https://api.binance.com/api/v3/"
    }

    init {
        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val clientBuilder: OkHttpClient.Builder =
            client.newBuilder().addInterceptor(interceptor as HttpLoggingInterceptor)

        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()

        api = retrofit!!.create(BinanceApi::class.java)
    }
}