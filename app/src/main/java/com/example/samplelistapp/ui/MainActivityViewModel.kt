package com.example.samplelistapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplelistapp.data.TradingPairsRepository
import com.example.samplelistapp.data.local.AppDatabase
import com.example.samplelistapp.data.network.ApiClient
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val tradingPairsRepository = TradingPairsRepository(
        ApiClient.INSTANCE.api,
        AppDatabase.getInstance().tradingPairDao()
    )

    val tradingPairs = tradingPairsRepository.getTradingPairs()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            tradingPairsRepository.fetchTradingPairs()
        }
    }

}