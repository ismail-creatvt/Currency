package com.ismail.creatvt.currency.data.repository

import com.ismail.creatvt.currency.data.remote.FixerApiService
import com.ismail.creatvt.currency.di.CoroutineScopeProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class ConversionRepository @Inject constructor(private val fixerApiService: FixerApiService, private val coroutineScopeProvider:CoroutineScopeProvider) {

    fun getAllSymbols(onResponse: (Map<String, String>) -> Unit, onError: (String) -> Unit) {
        coroutineScopeProvider.coroutineScope.launch {
            try {
                onResponse(fixerApiService.getSupportedSymbols()?.symbols?:throw Exception("Data is unavailable"))
            } catch (e: Exception) {
                onError(e.message?:"Unknown Error")
            }
        }
    }
}