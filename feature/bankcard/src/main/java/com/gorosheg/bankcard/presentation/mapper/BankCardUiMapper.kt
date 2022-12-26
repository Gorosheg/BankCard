package com.gorosheg.bankcard.presentation.mapper

import com.gorosheg.bankcard.presentation.model.BankCardItem.CardUi
import com.gorosheg.bankcard.presentation.model.BankCardItem.History
import com.gorosheg.bankcard.presentation.model.ShortCard
import com.gorosheg.pure.BankCard

internal fun BankCard?.mapToUiCard(): CardUi {
    return this?.mapToCard() ?: mapToEmptyCard()
}

internal fun List<BankCard>.mapToUiHistory(): History {
    return History(
        this
            .map(BankCard::mapToCard)
            .map(CardUi::mapToShortCard)
    )
}

private fun BankCard.mapToCard(): CardUi = CardUi(
    bankBin = cardBin,
    scheme = scheme,
    type = type,
    brand = brand,
    isPrepaid = isPrepaid.toString(),
    numberLength = cardNumber.length.toString(),
    numberLuhn = cardNumber.withLuhnAlgorithm.toString(),
    countryCode = country.code,
    countryName = country.name,
    latitude = country.latitude,
    longitude = country.longitude,
    bankNameAndCity = bank.nameAndCity,
    bankUrl = bank.url,
    bankPhone = bank.phone,
)

private fun CardUi.mapToShortCard(): ShortCard = ShortCard(
    scheme = scheme,
    cardBin = bankBin
)

private fun mapToEmptyCard(): CardUi {
    return CardUi(
        bankBin = "",
        scheme = "?",
        type = "?",
        brand = "?",
        isPrepaid = "?",
        numberLength = "?",
        numberLuhn = "?",
        countryCode = "?",
        countryName = "?",
        latitude = "?",
        longitude = "?",
        bankNameAndCity = "?",
        bankUrl = "?",
        bankPhone = "?",
    )
}