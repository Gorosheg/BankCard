package com.gorosheg.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal class CardEntity(
    @PrimaryKey
    val cardBin: Int,
    val cardNumber: CardNumberEntity,
    val scheme: String,
    val type: String,
    val brand: String,
    val isPrepaid: Boolean,
    @Embedded
    val country: CountryEntity,
    @Embedded
    val bank: BankEntity,
)