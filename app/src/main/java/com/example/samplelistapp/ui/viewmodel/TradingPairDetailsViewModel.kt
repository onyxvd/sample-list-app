package com.example.samplelistapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplelistapp.data.TradingPairsRepository
import com.example.samplelistapp.data.local.AppDatabase
import com.example.samplelistapp.data.local.entities.TradingPairEntity
import com.example.samplelistapp.data.network.ApiClient
import kotlinx.coroutines.launch

class TradingPairDetailsViewModel : ViewModel() {
    private val tradingPairsRepository = TradingPairsRepository(
        ApiClient.INSTANCE.api,
        AppDatabase.getInstance().tradingPairDao()
    )

    var tradingPairId: String = ""
        set(value) {
            field = value
            loadTradingPair()
        }

    val tradingPair = MutableLiveData<TradingPairEntity?>(null)

    private fun loadTradingPair() {
        viewModelScope.launch {
            tradingPair.value = tradingPairsRepository.getTradingPairById(tradingPairId)
        }
    }
}