package com.ismail.creatvt.currency.data.model

data class CurrencyRates(
    val code: String,
    val rate: Double,
    val value: Double
) {
    val convertedValue = value * rate
}