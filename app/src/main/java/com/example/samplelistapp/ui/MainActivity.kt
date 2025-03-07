package com.example.samplelistapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samplelistapp.R
import com.example.samplelistapp.ui.adapter.TradingPairsAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = getString(R.string.trading_pairs_list_title)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = TradingPairsAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.tradingPairsList)
        val emptyView = findViewById<View>(R.id.emptyView)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



        viewModel.tradingPairs.observe(this) { items ->
            if (items.isEmpty()) {
                recyclerView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE
            }

            adapter.setTradingPairs(items)
        }
    }
}