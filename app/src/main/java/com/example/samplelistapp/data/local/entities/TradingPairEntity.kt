package com.example.samplelistapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

//{
//    "symbol": "ETHBTC",
//    "priceChange": "0.00003000",
//    "priceChangePercent": "0.122",
//    "weightedAvgPrice": "0.02491120",
//    "prevClosePrice": "0.02469000",
//    "lastPrice": "0.02471000",
//    "lastQty": "0.52910000",
//    "bidPrice": "0.02471000",
//    "bidQty": "12.12280000",
//    "askPrice": "0.02472000",
//    "askQty": "69.16380000",
//    "openPrice": "0.02468000",
//    "highPrice": "0.02520000",
//    "lowPrice": "0.02464000",
//    "volume": "23114.85660000",
//    "quoteVolume": "575.81870833",
//    "openTime": 1741208166751,
//    "closeTime": 1741294566751,
//    "firstId": 489914843,
//    "lastId": 489969806,
//    "count": 54964
//}

@Entity
data class TradingPairEntity(
    @PrimaryKey val symbol: String,
    val priceChange: String?,
    val priceChangePercent: String?,
    val weightedAvgPrice: String?,
    val prevClosePrice: String?,
    val lastPrice: String?,
    val lastQty: String?,
    val bidPrice: String?,
    val bidQty: String?,
    val askPrice: String?,
    val askQty: String?,
    val openPrice: String?,
    val highPrice: String?,
    val lowPrice: String?,
    val volume: String?,
    val quoteVolume: String?,
    val openTime: Long?,
    val closeTime: Long?,
    val firstId: Long?,
    val lastId: Long?,
    val count: Long?
)
