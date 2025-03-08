package com.example.samplelistapp.data

import androidx.lifecycle.LiveData
import com.example.samplelistapp.asTradingPairEntity
import com.example.samplelistapp.data.local.dao.TradingPairDao
import com.example.samplelistapp.data.local.entities.TradingPairEntity
import com.example.samplelistapp.data.network.BinanceApi
import com.example.samplelistapp.data.network.models.NetworkTradingPair
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TradingPairsRepository(api: BinanceApi, dao: TradingPairDao) {

    private val binanceApi = api
    private val tradingPairDao = dao

    suspend fun fetchTradingPairs() {
        withContext(Dispatchers.IO) {
            val call = binanceApi.getTradingPairs()
            call.execute().body()?.let { networkTradingPairs ->
                val tradingPairs = networkTradingPairs.map(NetworkTradingPair::asTradingPairEntity)
                tradingPairDao.insertAll(tradingPairs)
            }
        }
    }

    fun getTradingPairs(): LiveData<List<TradingPairEntity>> {
        return tradingPairDao.getAllTradingPairs()
    }

    suspend fun getTradingPairById(id: String): TradingPairEntity {
        return tradingPairDao.getTradingPairById(id)
    }
}