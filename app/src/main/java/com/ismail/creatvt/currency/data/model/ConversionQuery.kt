package com.ismail.creatvt.currency.data.model

data class ConversionQuery(
    val from: String,
    val to: String,
    val amount: Double
)