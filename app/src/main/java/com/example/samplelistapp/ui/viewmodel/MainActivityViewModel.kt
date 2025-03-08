package com.example.samplelistapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
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

    val loading = MutableLiveData(false)

    val tradingPairs = tradingPairsRepository.getTradingPairs()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            loading.value = true
            tradingPairsRepository.fetchTradingPairs()
            loading.value = false
        }
    }

}