package com.gorosheg.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://lookup.binlist.net/"
private const val TIME_OUT: Long = 90

val networkModule = module {

    single<NetworkDataSource> {
        BankCardDataSource(get())
    }

    single<BankCardApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BankCardApi::class.java)
    }

    single {
        OkHttpClient().newBuilder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }
}