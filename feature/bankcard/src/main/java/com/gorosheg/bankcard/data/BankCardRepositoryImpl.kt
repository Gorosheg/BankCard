package com.gorosheg.bankcard.data

import com.gorosheg.bankcard.domain.BankCardRepository
import com.gorosheg.database.DatabaseDatasource
import com.gorosheg.network.NetworkDataSource
import com.gorosheg.pure.BankCard

internal class BankCardRepositoryImpl(
    private val networkDatasource: NetworkDataSource,
    private val databaseDatasource: DatabaseDatasource,
) : BankCardRepository {

    override suspend fun getCard(cardBin: String): BankCard? {
        return try {
            loadCard(cardBin).also { card ->
                addCardToDao(card, cardBin)
            }
        } catch (e: Exception) {
            getCardFromDao(cardBin)
        }
    }

    override fun getAllRequestedCards(): List<BankCard> {
        return databaseDatasource.getAll()
    }

    private suspend fun loadCard(cardBin: String): BankCard {
        return networkDatasource.getCardData(cardBin)
    }

    private fun addCardToDao(card: BankCard, cardBin: String) {
        return databaseDatasource.addCard(card, cardBin)
    }

    private fun getCardFromDao(cardBin: String): BankCard? {
        return databaseDatasource.getCard(cardBin)
    }
}