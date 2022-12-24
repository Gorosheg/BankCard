package com.gorosheg.database

import com.gorosheg.android.model.Bank
import com.gorosheg.android.model.BankCard
import com.gorosheg.android.model.CardNumber
import com.gorosheg.android.model.Country
import com.gorosheg.database.model.BankEntity
import com.gorosheg.database.model.CardEntity
import com.gorosheg.database.model.CardNumberEntity
import com.gorosheg.database.model.CountryEntity

interface DatabaseDatasource {

    fun addCard(card: BankCard, cardBin: Int)

    fun getCard(cardBin: Int): BankCard?

    fun getAll(): List<BankCard>?
}

internal class DatabaseDatasourceImpl(private val cardDao: CardDao) : DatabaseDatasource {

    override fun addCard(card: BankCard, cardBin: Int) {
        cardDao.insert(card.toEntity(cardBin))
    }

    override fun getCard(cardBin: Int): BankCard? {
        val card = cardDao.getByBin(cardBin)
        return card?.toBankCard() ?: return null
    }

    override fun getAll(): List<BankCard>? {
        return cardDao.getAll().toBankCardList()
    }

    private fun BankCard.toEntity(cardBin: Int): CardEntity {
        return CardEntity(
            cardBin = cardBin,
            cardNumber = CardNumberEntity(
                cardNumber.length,
                cardNumber.withLuhnAlgorithm
            ),
            scheme = scheme,
            type = type,
            brand = brand,
            isPrepaid = isPrepaid,
            country = CountryEntity(
                country.name,
                country.code,
                country.coordinates
            ),
            bank = BankEntity(
                nameAndCity = bank.nameAndCity,
                phone = bank.phone,
                url = bank.url
            )
        )
    }

    private fun List<CardEntity>?.toBankCardList(): List<BankCard>? {
        return this?.map { cardEntity ->
            cardEntity.toBankCard()
        }
    }

    private fun CardEntity.toBankCard(): BankCard {
        return BankCard(
            cardBin = cardBin.toString(),
            cardNumber = CardNumber(
                cardNumber.length,
                cardNumber.withLuhnAlgorithm
            ),
            scheme = scheme,
            type = type,
            brand = brand,
            isPrepaid = isPrepaid,
            country = Country(
                country.name,
                country.code,
                country.coordinates
            ),
            bank = Bank(
                nameAndCity = bank.nameAndCity,
                phone = bank.phone,
                url = bank.url
            )
        )
    }
}