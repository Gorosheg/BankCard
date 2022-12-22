package com.gorosheg.bankcard.presentation.recycler.models

import androidx.annotation.StringRes

internal sealed interface BankCardItems : ListItem {

    class History(
        val scheme: String,
        val cardBin: String,
    ) : BankCardItems

    class Header(
        @StringRes val title: Int,
    ) : BankCardItems

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
    ) : BankCardItems
}