@file:Suppress("UNCHECKED_CAST")

package com.ismail.creatvt.currency.utils

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter


@BindingAdapter("currencySymbols")
fun Spinner.setCurrencySymbols(symbols: Array<String>?) {
    adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, symbols?: arrayOf()).also {
        it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}

@BindingAdapter(value = ["selectedItem", "items"], requireAll = true)
fun setSelectedItem(view: AdapterView<*>, selected:String?, items:Array<String>?) {
    if(selected == null || items == null) return
    val position = items.indexOf(selected)

    if (view.selectedItemPosition != position) {
        view.setSelection(position)
    }
}