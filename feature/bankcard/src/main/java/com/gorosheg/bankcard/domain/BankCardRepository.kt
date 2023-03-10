package com.gorosheg.bankcard.domain

import com.gorosheg.pure.BankCard

internal interface BankCardRepository {

    suspend fun getCard(cardBin: String): BankCard?

    fun getAllRequestedCards(): List<BankCard>
}