package com.ismail.creatvt.currency.data.repository

import com.ismail.creatvt.currency.data.model.ConversionResponse
import com.ismail.creatvt.currency.data.model.CurrencyRates
import com.ismail.creatvt.currency.data.remote.FixerApiService
import com.ismail.creatvt.currency.di.CoroutineScopeProvider
import com.ismail.creatvt.currency.utils.TOP_10_CURRENCY_CODES
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConversionRepository @Inject constructor(
    private val fixerApiService: FixerApiService,
    private val coroutineScopeProvider: CoroutineScopeProvider
) {

    fun getAllSymbols(onResponse: (Map<String, String>) -> Unit, onError: (String) -> Unit) {
        coroutineScopeProvider.coroutineScope.launch {
            try {
                onResponse(
                    fixerApiService.getSupportedSymbols()?.symbols
                        ?: throw Exception("Data is unavailable")
                )
            } catch (e: Exception) {
                onError(e.message ?: "Unknown Error")
            }
        }
    }

    fun convert(
        from: String,
        to: String,
        amount: Double,
        onConversionDone: (Double) -> Unit,
        onError: (String) -> Unit
    ) {
        coroutineScopeProvider.coroutineScope.launch {
            try {
                onConversionDone(
                    fixerApiService.convertCurrency(from, to, amount)?.result
                        ?: throw Exception("Error while converting")
                )
            } catch (e: Exception) {
                onError(e.message ?: "Unknown Error")
            }
        }
    }

    fun getCountries(
        baseCurrency: String,
        value: Double,
        onResponse: (List<CurrencyRates>) -> Unit,
        onError: (String) -> Unit
    ) {
        coroutineScopeProvider.coroutineScope.launch {
            try {
                onResponse(
                    fixerApiService.getLatestRates(
                        baseCurrency,
                        TOP_10_CURRENCY_CODES
                    )?.rates?.map {
                        CurrencyRates(
                            it.key, it.value, value
                        )
                    } ?: throw Exception("Data not available")
                )
            } catch (e: Exception) {
                onError(e.message?:"Unknown Error")
            }
        }
    }
}