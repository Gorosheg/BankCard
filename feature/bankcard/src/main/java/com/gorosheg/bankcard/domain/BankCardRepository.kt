package com.gorosheg.bankcard.domain

import com.gorosheg.android.model.BankCard

internal interface BankCardRepository {

    suspend fun getCard(cardBin: Int): BankCard?
}