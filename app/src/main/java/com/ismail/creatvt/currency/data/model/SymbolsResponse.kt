package com.ismail.creatvt.currency.data.model

data class SymbolsResponse(
    val success: Boolean,
    val symbols: Map<String, String>
)