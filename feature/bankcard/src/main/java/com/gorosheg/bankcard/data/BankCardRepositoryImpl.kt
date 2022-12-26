package com.gorosheg.bankcard.data

import com.gorosheg.android.model.BankCard
import com.gorosheg.bankcard.domain.BankCardRepository
import com.gorosheg.database.DatabaseDatasource
import com.gorosheg.network.NetworkDataSource

internal class BankCardRepositoryImpl(
    private val networkDatasource: NetworkDataSource,
    private val databaseDatasource: DatabaseDatasource,
) : BankCardRepository {

    override suspend fun getCard(cardBin: Int): BankCard? {
        val card = try {
            loadCard(cardBin)
        } catch (e: Exception) {
            getCardFromDao(cardBin)
        }

        if (card != null) {
            addCardToDao(card, cardBin)
        }

        return card
    }

    override fun getAllRequestedCards(): List<BankCard>? {
        return databaseDatasource.getAll()
    }

    private suspend fun loadCard(cardBin: Int): BankCard {
        return networkDatasource.getCardData(cardBin)
    }

    private fun getCardFromDao(cardBin: Int): BankCard? {
        return databaseDatasource.getCard(cardBin)
    }

    private fun addCardToDao(card: BankCard, cardBin: Int) {
        return databaseDatasource.addCard(card, cardBin)
    }
}