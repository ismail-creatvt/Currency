package com.ismail.creatvt.currency.data.repository

import com.ismail.creatvt.currency.data.model.CurrencyRates
import com.ismail.creatvt.currency.data.model.HistoricalData
import com.ismail.creatvt.currency.data.remote.FixerApiService
import com.ismail.creatvt.currency.di.CoroutineScopeProvider
import com.ismail.creatvt.currency.utils.DATE_FORMAT
import com.ismail.creatvt.currency.utils.TOP_10_CURRENCY_CODES
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val fixerApiService: FixerApiService,
    private val coroutineScopeProvider: CoroutineScopeProvider
) {

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
                onError(e.message ?: "Unknown Error")
            }
        }
    }

    fun getHistoricalDataForLast3Days(
        from: String,
        to: String,
        value: Double,
        onResponse: (List<HistoricalData>) -> Unit,
        onError: (String) -> Unit
    ) {
        coroutineScopeProvider.coroutineScope.launch {
            val responseList = arrayListOf<HistoricalData>()
            val dates = getLast3Dates()
            try {
                dates.forEach {
                    val rates = fixerApiService.getHistoricalRates(it, from, to)?.rates
                    if (rates == null || rates.isEmpty()) {
                        throw Exception("Data is not available")
                    }
                    val code = rates.keys.toList()[0]
                    val rate = rates[code] ?: 0.0
                    responseList.add(HistoricalData(it, rate, value))
                }
            } catch (e: Exception) {
                onError(e.message ?: "Unknown Error")
            }
            onResponse(responseList)
        }
    }

    private fun getLast3Dates(): List<String> {
        val dates = arrayListOf<String>()
        val dateFormatter = SimpleDateFormat(DATE_FORMAT, Locale.US)
        val calendar = Calendar.getInstance()

        for (i in 0 until 3) {
            calendar.add(Calendar.DATE, i * -1)
            dates.add(dateFormatter.format(calendar.time))
            calendar.add(Calendar.DATE, i)
        }
        return dates
    }
}