package com.ismail.creatvt.currency.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.ismail.creatvt.currency.R
import com.ismail.creatvt.currency.databinding.ActivityDetailsBinding
import com.ismail.creatvt.currency.ui.details.historicaldata.HistoricalDataAdapter
import com.ismail.creatvt.currency.ui.details.othercountries.OtherCountriesAdapter
import com.ismail.creatvt.currency.utils.CURRENCY_VALUE
import com.ismail.creatvt.currency.utils.FROM_CURRENCY
import com.ismail.creatvt.currency.utils.TO_CURRENCY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val viewModel:DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val (from, to, value) = getDataFromExtras()

        binding.historicalAdapter = HistoricalDataAdapter(viewModel, this)
        binding.otherCountriesAdapter = OtherCountriesAdapter(viewModel, this)

        viewModel.error.observe(this) {
            if(it != null) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        if(savedInstanceState == null) {
            viewModel.loadOtherCountries(from, value)
            viewModel.loadHistoricalData(from, to, value)
        }
    }

    private fun getDataFromExtras(): Triple<String, String, Double> {
        val from = intent.getStringExtra(FROM_CURRENCY)?:""
        val to = intent.getStringExtra(TO_CURRENCY)?:""
        val value = intent.getDoubleExtra(CURRENCY_VALUE, 0.0)
        return Triple(from, to, value)
    }
}