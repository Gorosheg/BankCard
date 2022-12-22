package com.gorosheg.network

interface NetworkDataSource {
    suspend fun getCardData(cardBin: String?)
}

internal class BankCardDataSource(private val api: BankCardApi) : NetworkDataSource {

    override suspend fun getCardData(cardBin: String?) {
        api.getCardData()
    }
}