package com.gorosheg.bankcard.domain

internal interface BankCardRepository {

    suspend fun getCardData()
}