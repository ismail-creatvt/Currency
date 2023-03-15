package com.ismail.creatvt.currency.data.model

data class ConversionResponse(
    val success: Boolean,
    val query: ConversionQuery,
    val info: ConversionInfo,
    val historical: Boolean?,
    val date: String?,
    val result: Double
)
