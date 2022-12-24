package com.gorosheg.bankcard.presentation

import com.gorosheg.android.model.BankCard
import com.gorosheg.bankcard.presentation.model.BankCardItem

internal fun BankCard?.mapToUiCard(): BankCardItem.CardUi {
    return this?.mapToCard() ?: mapToEmptyCard()
}

private fun BankCard.mapToCard(): BankCardItem.CardUi {
    return BankCardItem.CardUi(
        scheme = scheme,
        type = type,
        brand = brand,
        isPrepaid = isPrepaid.toString(),
        numberLength = cardNumber.length.toString(),
        numberLuhn = cardNumber.withLuhnAlgorithm.toString(),
        countryCode = country.code,
        countryName = country.name,
        countryCoordinates = country.coordinates,
        bankNameAndCity = bank.nameAndCity,
        bankUrl = bank.url,
        bankPhone = bank.phone,
    )
}

private fun mapToEmptyCard(): BankCardItem.CardUi {
    return BankCardItem.CardUi(
        scheme = "?",
        type = "?",
        brand = "?",
        isPrepaid = "?",
        numberLength = "?",
        numberLuhn = "?",
        countryCode = "?",
        countryName = "?",
        countryCoordinates = "?",
        bankNameAndCity = "?",
        bankUrl = "?",
        bankPhone = "?",
    )
}
