package com.ismail.creatvt.currency.di

import kotlinx.coroutines.CoroutineScope

interface CoroutineScopeProvider {
    val coroutineScope: CoroutineScope
}