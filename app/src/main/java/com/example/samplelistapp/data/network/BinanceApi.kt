package com.example.samplelistapp.data.network

import com.example.samplelistapp.data.network.models.NetworkTradingPair
import retrofit2.Call
import retrofit2.http.GET

interface BinanceApi {
    @GET("ticker/24hr")
    fun getTradingPairs(): Call<List<NetworkTradingPair>>
}
