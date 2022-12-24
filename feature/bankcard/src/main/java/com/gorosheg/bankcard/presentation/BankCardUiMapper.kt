package com.gorosheg.bankcard.presentation

import com.gorosheg.android.model.BankCard
import com.gorosheg.bankcard.presentation.model.BankCardItem
import com.gorosheg.bankcard.presentation.model.ShortCard

internal fun BankCard?.mapToUiCard(): BankCardItem.CardUi {
    return this?.mapToCard() ?: mapToEmptyCard()
}

internal fun List<BankCard>?.mapToUiHistory(): BankCardItem.History {
    return this?.mapToListOfHistory() ?: BankCardItem.History(emptyList())
}

private fun BankCard.mapToCard(): BankCardItem.CardUi {
    return BankCardItem.CardUi(
        bankBin = cardBin,
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
        bankBin = "",
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

private fun List<BankCard>.mapToListOfHistory(): BankCardItem.History {
    return BankCardItem.History(
        this
            .map { bankCard ->
                bankCard.mapToCard()
            }
            .map { cardUi ->
                ShortCard(cardUi.scheme, cardUi.bankBin)
            }
    )
}
