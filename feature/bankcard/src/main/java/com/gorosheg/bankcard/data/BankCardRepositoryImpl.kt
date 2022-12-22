package com.gorosheg.bankcard.data

import com.gorosheg.bankcard.domain.BankCardRepository
import com.gorosheg.network.NetworkDataSource

internal class BankCardRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
) : BankCardRepository {

    override suspend fun getCardData() {
        networkDataSource.getCardData(null)
    }
}