package com.ismail.creatvt.currency.data.model

data class HistoricalRatesResponse(
    val success: Boolean,
    val historical: Boolean,
    val date: String,
    val timestamp: Long,
    val base: String,
    val rates: Map<String, Double>
)