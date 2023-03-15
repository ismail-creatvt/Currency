package com.ismail.creatvt.currency

import android.app.Application
import com.ismail.creatvt.currency.di.CoroutineScopeProvider
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class CurrencyApplication : Application(), CoroutineScopeProvider {

    override val coroutineScope: CoroutineScope by lazy {
        CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    }
}