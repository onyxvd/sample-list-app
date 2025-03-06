package com.example.samplelistapp

import android.app.Application
import com.example.samplelistapp.data.local.AppDatabase

class SampleListAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        AppDatabase.init(this)
    }
}