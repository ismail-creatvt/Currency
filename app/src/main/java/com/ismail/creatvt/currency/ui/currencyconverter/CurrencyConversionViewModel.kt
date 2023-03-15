package com.ismail.creatvt.currency.ui.currencyconverter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ismail.creatvt.currency.data.remote.FixerApiService
import com.ismail.creatvt.currency.data.repository.ConversionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyConversionViewModel @Inject constructor(val conversionRepository: ConversionRepository) :
    ViewModel() {

    private val _currencies = MutableLiveData<Map<String, String>>()

    val currencies: LiveData<Map<String, String>>
        get() = _currencies

    private val _error = MutableLiveData<String>(null)
    val error: LiveData<String>
        get() = _error

    fun loadCurrencies() {
        conversionRepository.getAllSymbols({
            _currencies.postValue(it)
        }) {
            _error.postValue(it)
        }
    }

}