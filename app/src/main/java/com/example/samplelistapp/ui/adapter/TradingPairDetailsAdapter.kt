package com.example.samplelistapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.samplelistapp.R

data class DetailItem(
    val label: String,
    val value: String
)

class DetailsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val label: TextView = itemView.findViewById(R.id.detail_label)
    private val value: TextView = itemView.findViewById(R.id.detail_value)

    fun bind(detailItem: DetailItem) {
        label.text = detailItem.label
        value.text = detailItem.value
    }
}

class TradingPairDetailsAdapter() :
    RecyclerView.Adapter<DetailsItemViewHolder>() {

    private val details = mutableListOf<DetailItem>()

    fun setDetails(details: List<DetailItem>) {
        this.details.clear()
        this.details.addAll(details)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
        return DetailsItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailsItemViewHolder, position: Int) {
        holder.bind(details[position])
    }

    override fun getItemCount(): Int {
        return details.size
    }

}