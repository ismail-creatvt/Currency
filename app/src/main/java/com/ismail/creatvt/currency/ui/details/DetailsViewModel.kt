package com.ismail.creatvt.currency.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ismail.creatvt.currency.data.model.CurrencyRates
import com.ismail.creatvt.currency.data.model.HistoricalData
import com.ismail.creatvt.currency.data.repository.ConversionRepository
import com.ismail.creatvt.currency.data.repository.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val detailsRepository: DetailsRepository) :
    ViewModel() {

    private val _otherCountries = MutableLiveData<List<CurrencyRates>>(listOf())
    val otherCountries: LiveData<List<CurrencyRates>>
        get() = _otherCountries

    private val _historicalData = MutableLiveData<List<HistoricalData>>(listOf())
    val historicalData: LiveData<List<HistoricalData>>
        get() = _historicalData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun loadOtherCountries(baseCurrency: String, value:Double) {
        detailsRepository.getCountries(baseCurrency, value, {
            _otherCountries.postValue(it)
        }) {
            _error.postValue(it)
        }
    }

    fun loadHistoricalData(from: String, to:String, value: Double) {
        detailsRepository.getHistoricalDataForLast3Days(from, to, value, {
            _historicalData.postValue(it)
        }) {
            _error.postValue(it)
        }
    }
}