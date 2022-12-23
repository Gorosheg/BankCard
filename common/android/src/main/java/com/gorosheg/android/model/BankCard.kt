package com.gorosheg.android.model

class BankCard(
    val cardNumber: CardNumber,
    val scheme: String,
    val type: String,
    val brand: String,
    val isPrepaid: Boolean,
    val country: Country,
    val bank: Bank,
)