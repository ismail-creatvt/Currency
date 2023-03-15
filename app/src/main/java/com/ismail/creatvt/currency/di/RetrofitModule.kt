package com.ismail.creatvt.currency.di

import com.ismail.creatvt.currency.data.remote.ApiKeyInterceptor
import com.ismail.creatvt.currency.data.remote.FixerApiService
import com.ismail.creatvt.currency.utils.FIXER_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideCurrencyService(retrofit: Retrofit): FixerApiService {
        return retrofit.create(FixerApiService::class.java)
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FIXER_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor()).build()
    }

}