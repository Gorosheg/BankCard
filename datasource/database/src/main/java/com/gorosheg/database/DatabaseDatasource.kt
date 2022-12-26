package com.gorosheg.database

import com.gorosheg.database.model.BankEntity
import com.gorosheg.database.model.CardEntity
import com.gorosheg.database.model.CardNumberEntity
import com.gorosheg.database.model.CountryEntity
import com.gorosheg.pure.Bank
import com.gorosheg.pure.BankCard
import com.gorosheg.pure.CardNumber
import com.gorosheg.pure.Country

interface DatabaseDatasource {

    fun addCard(card: BankCard, cardBin: String)

    fun getCard(cardBin: String): BankCard?

    fun getAll(): List<BankCard>
}

internal class DatabaseDatasourceImpl(private val cardDao: CardDao) : DatabaseDatasource {

    override fun addCard(card: BankCard, cardBin: String) {
        cardDao.insert(card.toEntity(cardBin))
    }

    override fun getCard(cardBin: String): BankCard? {
        val card = cardDao.getByBin(cardBin)
        return card?.toBankCard() ?: return null
    }

    override fun getAll(): List<BankCard> {
        return cardDao.getAll().toBankCardList()
    }

    private fun BankCard.toEntity(cardBin: String) = CardEntity(
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
            country.latitude,
            country.longitude
        ),
        bank = BankEntity(
            nameAndCity = bank.nameAndCity,
            phone = bank.phone,
            url = bank.url
        )
    )

    private fun List<CardEntity>.toBankCardList(): List<BankCard> {
        return map { cardEntity ->
            cardEntity.toBankCard()
        }
    }

    private fun CardEntity.toBankCard() = BankCard(
        cardBin = cardBin,
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
            country.latitude,
            country.longitude
        ),
        bank = Bank(
            nameAndCity = bank.nameAndCity,
            phone = bank.phone,
            url = bank.url
        )
    )
}