package com.ismail.creatvt.currency.data.remote

import com.ismail.creatvt.currency.data.model.ConversionResponse
import com.ismail.creatvt.currency.data.model.HistoricalRatesResponse
import com.ismail.creatvt.currency.data.model.LatestRatesResponse
import com.ismail.creatvt.currency.data.model.SymbolsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FixerApiService {
    @GET("symbols")
    suspend fun getSupportedSymbols(): SymbolsResponse?

    @GET("latest")
    suspend fun getLatestRates(
        @Query("base") baseCurrency: String?,
        @Query("symbols") symbols: String?
    ): LatestRatesResponse?

    @GET("{date}")
    suspend fun getHistoricalRates(
        @Path("date") date: String?,
        @Query("base") baseCurrency: String?,
        @Query("symbols") symbols: String?
    ): HistoricalRatesResponse?

    @GET("convert")
    suspend fun convertCurrency(
        @Query("from") fromCurrency: String?,
        @Query("to") toCurrency: String?,
        @Query("amount") amount: Double
    ): ConversionResponse?
}