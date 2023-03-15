package com.ismail.creatvt.currency.utils

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter


@BindingAdapter("currencySymbols")
fun Spinner.setCurrencySymbols(symbols: Map<String, String>?) {
    adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, symbols?.keys?.toTypedArray()?: arrayOf()).also {
        it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}