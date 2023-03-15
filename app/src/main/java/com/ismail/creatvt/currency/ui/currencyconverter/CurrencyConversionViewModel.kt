package com.ismail.creatvt.currency.ui.currencyconverter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ismail.creatvt.currency.data.repository.ConversionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyConversionViewModel @Inject constructor(val conversionRepository: ConversionRepository) :
    ViewModel() {

    private val _currencies = MutableLiveData<Array<String>>()

    val currencies: LiveData<Array<String>>
        get() = _currencies

    var navigateToDetails: ((String, String, Double) -> Unit)? = null

    private val _toText = MutableLiveData("")
    val toText: LiveData<String>
        get() = _toText

    private val _fromText = MutableLiveData("")
    val fromText: LiveData<String>
        get() = _fromText


    private val _toCurrency = MutableLiveData("")
    val toCurrency: LiveData<String>
        get() = _toCurrency

    private val _fromCurrency = MutableLiveData("")
    val fromCurrency: LiveData<String>
        get() = _fromCurrency

    private val _error = MutableLiveData<String>(null)
    val error: LiveData<String>
        get() = _error

    fun loadCurrencies() {
        conversionRepository.getAllSymbols({
            _currencies.postValue(it.keys.toTypedArray())
        }) {
            _error.postValue(it)
        }
    }

    fun onDetailsClicked() {
        val (from, to, value) = getValues()
        navigateToDetails?.invoke(from, to, value)
    }

    private fun getValues(): Triple<String, String, Double> {
        val from = _fromCurrency.value ?: ""
        val to = _toCurrency.value ?: ""
        val inputValue = _fromText.value?.toDoubleOrNull() ?: 0.0
        return Triple(from, to, inputValue)
    }

    fun onEditToField(text: CharSequence) {
        _fromText.value = text.toString()

        convertCurrencies()
    }

    fun onToCurrencySelected(position: Int) {
        _toCurrency.value = _currencies.value?.get(position) ?: ""

        convertCurrencies()
    }

    fun onFromCurrencySelected(position: Int) {
        _fromCurrency.value = _currencies.value?.get(position) ?: ""

        convertCurrencies()
    }

    fun onClickSwap() {
        val from = _fromCurrency.value
        _fromCurrency.value = _toCurrency.value
        _toCurrency.value = from

        convertCurrencies()
    }

    private fun convertCurrencies() {
        val (from, to, value) = getValues()
        conversionRepository.convert(from, to, value, {
            _toText.postValue(String.format("%.2f", it))
        }) {
            _error.postValue(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        navigateToDetails = null
    }
}