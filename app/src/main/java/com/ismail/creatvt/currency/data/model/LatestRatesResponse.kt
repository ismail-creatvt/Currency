package com.ismail.creatvt.currency.data.model

data class LatestRatesResponse(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)