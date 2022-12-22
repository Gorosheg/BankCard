package com.gorosheg.network

import retrofit2.http.GET

internal interface BankCardApi {

    @GET("45717360")
    suspend fun getCardData()
}