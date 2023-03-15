package com.ismail.creatvt.currency.ui.details.historicaldata

import androidx.recyclerview.widget.DiffUtil
import com.ismail.creatvt.currency.data.model.HistoricalData

class HistoricalDataDiffCallback(private val oldItems:List<HistoricalData>, private val newItems:List<HistoricalData>): DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.convertedValue == newItem.convertedValue
    }


}