package com.gorosheg.pure

class BankCard(
    val cardBin: String,
    val cardNumber: CardNumber,
    val scheme: String,
    val type: String,
    val brand: String,
    val isPrepaid: Boolean,
    val country: Country,
    val bank: Bank,
)