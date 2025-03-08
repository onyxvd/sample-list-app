package com.example.samplelistapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samplelistapp.R
import com.example.samplelistapp.ui.adapter.DetailItem
import com.example.samplelistapp.ui.adapter.TradingPairDetailsAdapter
import com.example.samplelistapp.ui.viewmodel.TradingPairDetailsViewModel

class TradingPairDetailsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_TRADING_PAIR_SYMBOL = "trading_pair_id"

        fun newIntent(context: Context, tradingPairId: String): Intent {
            return Intent(context, TradingPairDetailsActivity::class.java).apply {
                putExtra(EXTRA_TRADING_PAIR_SYMBOL, tradingPairId)
            }
        }
    }

    private val viewModel: TradingPairDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_trading_pair_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tradingPairId = intent.getStringExtra(EXTRA_TRADING_PAIR_SYMBOL) ?: ""
        viewModel.tradingPairId = tradingPairId

        val adapter = TradingPairDetailsAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.tradingPairsDetailsList)
        val emptyView = findViewById<View>(R.id.emptyView)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.tradingPair.observe(this) { tradingPair ->
            if (tradingPair == null) {
                recyclerView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            } else {
                supportActionBar?.title = tradingPair.symbol

                recyclerView.visibility = View.VISIBLE
                emptyView.visibility = View.GONE

                with(tradingPair) {
                    adapter.setDetails(
                        listOf(
                            DetailItem(getString(R.string.details_symbol), symbol),
                            DetailItem(
                                getString(R.string.details_price_change),
                                priceChange ?: ""
                            ),
                            DetailItem(
                                getString(R.string.details_price_change_percent),
                                priceChangePercent ?: ""
                            ),
                            DetailItem(
                                getString(R.string.details_weighted_avg_price),
                                weightedAvgPrice ?: ""
                            ),
                            DetailItem(
                                getString(R.string.details_prev_close_price),
                                prevClosePrice ?: ""
                            ),
                            DetailItem(getString(R.string.details_last_price), lastPrice ?: ""),
                            DetailItem(getString(R.string.details_last_qty), lastQty ?: ""),
                            DetailItem(getString(R.string.details_bid_price), bidPrice ?: ""),
                            DetailItem(getString(R.string.details_bid_qty), bidQty ?: ""),
                            DetailItem(getString(R.string.details_ask_price), askPrice ?: ""),
                            DetailItem(getString(R.string.details_ask_qty), askQty ?: ""),
                            DetailItem(getString(R.string.details_open_price), openPrice ?: ""),
                            DetailItem(getString(R.string.details_high_price), highPrice ?: ""),
                            DetailItem(getString(R.string.details_low_price), lowPrice ?: ""),
                            DetailItem(getString(R.string.details_volume), volume ?: ""),
                            DetailItem(
                                getString(R.string.details_quote_volume),
                                quoteVolume ?: ""
                            ),
                            DetailItem(
                                getString(R.string.details_open_time),
                                openTime?.toString() ?: ""
                            ),
                            DetailItem(
                                getString(R.string.details_close_time),
                                closeTime?.toString() ?: ""
                            ),
                            DetailItem(
                                getString(R.string.details_first_id),
                                firstId?.toString() ?: ""
                            ),
                            DetailItem(
                                getString(R.string.details_last_id),
                                lastId?.toString() ?: ""
                            ),
                            DetailItem(
                                getString(R.string.details_count),
                                count?.toString() ?: ""
                            )
                        )
                    )
                }

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressedDispatcher.onBackPressed()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}