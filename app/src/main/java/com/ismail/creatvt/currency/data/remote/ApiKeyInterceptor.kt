package com.ismail.creatvt.currency.data.remote

import com.ismail.creatvt.currency.utils.FIXER_API_KEY
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


class ApiKeyInterceptor @Inject constructor():Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val updatedRequest = chain.request().newBuilder()
            .addHeader("apiKey", FIXER_API_KEY)
            .build()

        return chain.proceed(updatedRequest)
    }
}