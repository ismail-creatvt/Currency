package com.ismail.creatvt.currency.ui.details.historicaldata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ismail.creatvt.currency.R
import com.ismail.creatvt.currency.data.model.HistoricalData
import com.ismail.creatvt.currency.databinding.HistoricalDataItemBinding
import com.ismail.creatvt.currency.ui.details.DetailsViewModel

class HistoricalDataAdapter(viewModel: DetailsViewModel, lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<HistoricalDataAdapter.HistoricalViewHolder>() {

    private var historicalData: List<HistoricalData> = listOf()
        set(value) {
            val oldItems = field
            field = value
            DiffUtil.calculateDiff(HistoricalDataDiffCallback(oldItems, field))
                .dispatchUpdatesTo(this)
        }

    init {
        viewModel.historicalData.observe(lifecycleOwner) {
            historicalData = it
        }
    }

    class HistoricalViewHolder(val itemBinding: HistoricalDataItemBinding) :
        ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HistoricalViewHolder(
        DataBindingUtil.inflate<HistoricalDataItemBinding?>(
            LayoutInflater.from(parent.context),
            R.layout.historical_data_item,
            parent,
            false
        )
    )

    override fun getItemCount() = historicalData.size

    override fun onBindViewHolder(holder: HistoricalViewHolder, position: Int) {
        holder.itemBinding.data = historicalData[position]
    }

}