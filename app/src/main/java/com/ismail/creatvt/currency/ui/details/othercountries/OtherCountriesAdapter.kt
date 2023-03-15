package com.ismail.creatvt.currency.ui.details.othercountries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ismail.creatvt.currency.R
import com.ismail.creatvt.currency.data.model.CurrencyRates
import com.ismail.creatvt.currency.databinding.OtherCountriesDataItemBinding
import com.ismail.creatvt.currency.ui.details.DetailsViewModel

class OtherCountriesAdapter(viewModel: DetailsViewModel, lifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<OtherCountriesAdapter.HistoricalViewHolder>() {

    private var otherCountriesData: List<CurrencyRates> = listOf()
        set(value) {
            val oldItems = field
            field = value
            DiffUtil.calculateDiff(OtherCountriesDiffCallback(oldItems, field))
                .dispatchUpdatesTo(this)
        }

    init {
        viewModel.otherCountries.observe(lifecycleOwner) {
            otherCountriesData = it
        }
    }

    class HistoricalViewHolder(val itemBinding: OtherCountriesDataItemBinding) :
        ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HistoricalViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.other_countries_data_item,
            parent,
            false
        )
    )

    override fun getItemCount() = otherCountriesData.size

    override fun onBindViewHolder(holder: HistoricalViewHolder, position: Int) {
        holder.itemBinding.data = otherCountriesData[position]
    }

}