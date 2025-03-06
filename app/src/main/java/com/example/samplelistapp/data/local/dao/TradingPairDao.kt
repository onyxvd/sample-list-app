package com.example.samplelistapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.samplelistapp.data.local.entities.TradingPairEntity

@Dao
interface TradingPairDao {
    @Query("SELECT * FROM TradingPairEntity")
    suspend fun getAllTradingPairs(): LiveData<List<TradingPairEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tradingPairs: List<TradingPairEntity>)
}