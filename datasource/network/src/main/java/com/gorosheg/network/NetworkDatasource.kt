package com.gorosheg.network

import com.gorosheg.android.model.Bank
import com.gorosheg.android.model.BankCard
import com.gorosheg.android.model.CardNumber
import com.gorosheg.android.model.Country
import com.gorosheg.network.model.BankCardResponse

interface NetworkDataSource {
    suspend fun getCardData(cardBin: Int): BankCard
}

internal class BankCardDataSource(private val api: BankCardApi) : NetworkDataSource {

    override suspend fun getCardData(cardBin: Int): BankCard {
        return api.getCardData(cardBin.toString()).toBankCard(cardBin)
    }

    private fun BankCardResponse.toBankCard(cardBin: Int): BankCard {
        return BankCard(
            cardBin = cardBin.toString(),
            cardNumber = CardNumber(cardNumber.length, cardNumber.withLuhnAlgorithm),
            scheme = scheme,
            type = type,
            brand = brand,
            isPrepaid = isPrepaid,
            country = buildCountry(),
            bank = buildBank()
        )
    }

    private fun BankCardResponse.buildCountry() = Country(
        country.name,
        country.code,
        "(latitude: ${country.latitude}, longitude: ${country.longitude})"
    )

    private fun BankCardResponse.buildBank() = Bank(
        nameAndCity = "${bank.name} ${bank.city}",
        url = bank.url,
        phone = bank.phone
    )
}