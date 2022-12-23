package com.gorosheg.network

import com.gorosheg.network.model.BankCardResponse
import retrofit2.http.GET
import retrofit2.http.Path

internal interface BankCardApi {

    @GET("{cardbin}")
    suspend fun getCardData(
        @Path("cardbin") cardBin: String,
    ): BankCardResponse
}