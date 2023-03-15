package com.ismail.creatvt.currency.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ismail.creatvt.currency.data.model.CurrencyRates
import com.ismail.creatvt.currency.data.repository.ConversionRepository
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val conversionRepository: ConversionRepository) :
    ViewModel() {

    private val _otherCountries = MutableLiveData<List<CurrencyRates>>()
    val otherCountries: LiveData<List<CurrencyRates>>
        get() = _otherCountries

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun loadOtherCountries(baseCurrency: String, value:Double) {
        conversionRepository.getCountries(baseCurrency, value, {
            _otherCountries.postValue(it)
        }) {
            _error.postValue(it)
        }
    }
}