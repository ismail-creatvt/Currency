package com.ismail.creatvt.currency.ui.details.othercountries

import androidx.recyclerview.widget.DiffUtil
import com.ismail.creatvt.currency.data.model.CurrencyRates
import com.ismail.creatvt.currency.data.model.HistoricalData

class OtherCountriesDiffCallback(private val oldItems:List<CurrencyRates>, private val newItems:List<CurrencyRates>): DiffUtil.Callback() {
    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.convertedValue == newItem.convertedValue
    }


}