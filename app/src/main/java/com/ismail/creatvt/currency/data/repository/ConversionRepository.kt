package com.ismail.creatvt.currency.data.repository

import com.ismail.creatvt.currency.data.model.CurrencyRates
import com.ismail.creatvt.currency.data.model.HistoricalData
import com.ismail.creatvt.currency.data.model.HistoricalRatesResponse
import com.ismail.creatvt.currency.data.remote.FixerApiService
import com.ismail.creatvt.currency.di.CoroutineScopeProvider
import com.ismail.creatvt.currency.utils.DATE_FORMAT
import com.ismail.creatvt.currency.utils.TOP_10_CURRENCY_CODES
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

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

}