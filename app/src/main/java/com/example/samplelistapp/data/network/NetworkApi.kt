package com.example.samplelistapp.data.network

import com.example.samplelistapp.data.network.models.NetworkTradingPair
import retrofit2.http.GET

interface NetworkApi {
    @GET("ticker/24hr")
    suspend fun getTradingPairs(): List<NetworkTradingPair>
}
