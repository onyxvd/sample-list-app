package com.example.samplelistapp

import com.example.samplelistapp.data.local.entities.TradingPairEntity
import com.example.samplelistapp.data.network.models.NetworkTradingPair

fun NetworkTradingPair.asTradingPairEntity() =
    TradingPairEntity(
        symbol = symbol,
        priceChange = priceChange,
        priceChangePercent = priceChangePercent,
        weightedAvgPrice = weightedAvgPrice,
        prevClosePrice = prevClosePrice,
        lastPrice = lastPrice,
        lastQty = lastQty,
        bidPrice = bidPrice,
        bidQty = bidQty,
        askPrice = askPrice,
        askQty = askQty,
        openPrice = openPrice,
        highPrice = highPrice,
        lowPrice = lowPrice,
        volume = volume,
        quoteVolume = quoteVolume,
        openTime = openTime,
        closeTime = closeTime,
        firstId = firstId,
        lastId = lastId,
        count = count
    )