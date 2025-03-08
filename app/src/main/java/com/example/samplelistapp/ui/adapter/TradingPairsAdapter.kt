package com.example.samplelistapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.samplelistapp.R
import com.example.samplelistapp.data.local.entities.TradingPairEntity

class TradingPairViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val symbol: TextView = itemView.findViewById(R.id.symbol)
    private val priceChangePercent: TextView = itemView.findViewById(R.id.priceChangePercent)
    private val bidAsk: TextView = itemView.findViewById(R.id.bidAsk)
    private val itemContainer: View = itemView.findViewById(R.id.itemContainer)

    fun bind(tradingPair: TradingPairEntity, itemClickListener: OnItemClickListener) {
        symbol.text = tradingPair.symbol
        priceChangePercent.text = itemView.context.getString(
            R.string.price_change_percent,
            tradingPair.priceChangePercent
        )
        bidAsk.text = itemView.context.getString(
            R.string.bid_ask_values,
            tradingPair.bidPrice,
            tradingPair.askPrice
        )

        itemContainer.setOnClickListener {
            itemClickListener.onItemClicked(tradingPair)
        }
    }
}

interface OnItemClickListener {
    fun onItemClicked(tradingPair: TradingPairEntity)
}

class TradingPairsAdapter(private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<TradingPairViewHolder>() {

    private val tradingPairs = mutableListOf<TradingPairEntity>()

    init {
        setHasStableIds(true)
    }

    fun setTradingPairs(tradingPairs: List<TradingPairEntity>) {
        this.tradingPairs.clear()
        this.tradingPairs.addAll(tradingPairs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradingPairViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_trading_pair, parent, false)
        return TradingPairViewHolder(view)
    }

    override fun onBindViewHolder(holder: TradingPairViewHolder, position: Int) {
        holder.bind(tradingPairs[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return tradingPairs.size
    }

}