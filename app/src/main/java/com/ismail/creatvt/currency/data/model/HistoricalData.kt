package com.ismail.creatvt.currency.data.model

data class HistoricalData(
    val date:String,
    val rate: Double,
    val value: Double
) {
    val convertedValue = rate * value
}
