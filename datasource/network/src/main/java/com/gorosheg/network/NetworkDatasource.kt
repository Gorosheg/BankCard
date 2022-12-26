package com.gorosheg.network

import com.gorosheg.network.model.BankCardResponse
import com.gorosheg.pure.Bank
import com.gorosheg.pure.BankCard
import com.gorosheg.pure.CardNumber
import com.gorosheg.pure.Country

interface NetworkDataSource {
    suspend fun getCardData(cardBin: String): BankCard
}

internal class BankCardDataSource(private val api: BankCardApi) : NetworkDataSource {

    override suspend fun getCardData(cardBin: String): BankCard {
        return api.getCardData(cardBin).toBankCard(cardBin)
    }

    private fun BankCardResponse.toBankCard(cardBin: String): BankCard {
        return BankCard(
            cardBin = cardBin,
            cardNumber = CardNumber(number.length, number.withLuhnAlgorithm),
            scheme = scheme,
            type = type,
            brand = brand,
            isPrepaid = isPrepaid,
            country = buildCountry(),
            bank = buildBank()
        )
    }

    private fun BankCardResponse.buildCountry() = Country(
        name = country.name,
        code = country.code,
        latitude = country.latitude.toString(),
        longitude = country.longitude.toString()
    )

    private fun BankCardResponse.buildBank() = Bank(
        nameAndCity = "${bank.name} ${bank.city}",
        url = bank.url,
        phone = bank.phone
    )
}