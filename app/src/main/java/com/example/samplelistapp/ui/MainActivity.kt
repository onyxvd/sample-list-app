package com.example.samplelistapp.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.samplelistapp.R
import com.example.samplelistapp.data.local.entities.TradingPairEntity
import com.example.samplelistapp.ui.adapter.OnItemClickListener
import com.example.samplelistapp.ui.adapter.TradingPairsAdapter
import com.example.samplelistapp.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var adapter: TradingPairsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: View
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar


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

        initViews()

        viewModel.loading.observe(this) { loading ->
            // Show progress bar only if swipe to refresh is not in progress
            if (!swipeRefreshLayout.isRefreshing) {
                progressBar.visibility = if (loading) View.VISIBLE else View.GONE
            }
        }

        viewModel.tradingPairs.observe(this) { items ->
            onDataReceived(items)
        }
    }

    private fun onDataReceived(items: List<TradingPairEntity>) {
        swipeRefreshLayout.isRefreshing = false
        adapter.setTradingPairs(items)

        if (items.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyView.visibility = View.GONE
        }
    }

    private fun initViews() {
        adapter = TradingPairsAdapter(this)
        recyclerView = findViewById(R.id.tradingPairsList)
        emptyView = findViewById(R.id.emptyView)
        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        progressBar = findViewById(R.id.progressBar)

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onItemClicked(tradingPair: TradingPairEntity) {
        startActivity(TradingPairDetailsActivity.newIntent(this, tradingPair.symbol))
    }
}