package com.example.samplelistapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.samplelistapp.data.local.dao.TradingPairDao
import com.example.samplelistapp.data.local.entities.TradingPairEntity

@Database(entities = [TradingPairEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tradingPairDao(): TradingPairDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(): AppDatabase {
            checkNotNull(INSTANCE) { "Instance is null. init(Context context) must be called before getting the instance." }
            return INSTANCE!!
        }

        fun init(context: Context) {
            if (INSTANCE != null) {
                return
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sample-list-app-db"
                ).build()
                INSTANCE = instance
            }
        }
    }
}