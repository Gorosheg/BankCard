package com.gorosheg.bankcard.presentation.models

import androidx.annotation.StringRes

internal sealed interface BankCardItem : ListItem {

    class History(
        val scheme: String,
        val cardBin: String,
    ) : BankCardItem

    class Header(
        @StringRes val title: Int,
    ) : BankCardItem

    class Card(
        val scheme: String,
        val type: String,
        val brand: String,
        val prepaid: String,
        val numberLength: String,
        val numberLuhn: String,

        val countryCode: String,
        val countryName: String,
        val countryLatitude: String,
        val countryLongitude: String,

        val bankName: String,
        val bankUrl: String,
        val bankPhone: String,
        val bankCity: String,
    ) : BankCardItem
}