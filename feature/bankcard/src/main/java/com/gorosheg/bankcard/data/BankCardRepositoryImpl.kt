package com.gorosheg.bankcard.data

import com.gorosheg.android.model.BankCard
import com.gorosheg.bankcard.domain.BankCardRepository
import com.gorosheg.network.NetworkDataSource

internal class BankCardRepositoryImpl(
    private val networkDataSource: NetworkDataSource,
) : BankCardRepository {

    override suspend fun getCard(cardBin: Int): BankCard {
        return networkDataSource.getCardData(cardBin)
    }
}