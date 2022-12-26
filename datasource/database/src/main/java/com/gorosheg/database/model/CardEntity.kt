package com.gorosheg.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cardBin: String,
    @Embedded
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