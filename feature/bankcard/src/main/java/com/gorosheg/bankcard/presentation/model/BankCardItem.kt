package com.gorosheg.bankcard.presentation.model

import androidx.annotation.StringRes

internal sealed interface BankCardItem : ListItem {

    class History(
        val scheme: String,
        val cardBin: String,
    ) : BankCardItem

    class Header(
        @StringRes val title: Int,
    ) : BankCardItem

    class CardUi(
        val scheme: String,
        val type: String,
        val brand: String,
        val isPrepaid: String,
        val numberLength: String,
        val numberLuhn: String,

        val countryCode: String,
        val countryName: String,
        val countryCoordinates: String,

        val bankNameAndCity: String,
        val bankUrl: String,
        val bankPhone: String,
    ) : BankCardItem
}