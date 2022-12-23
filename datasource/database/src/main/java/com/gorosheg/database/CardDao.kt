package com.gorosheg.database

import androidx.room.Insert
import androidx.room.Query
import com.gorosheg.database.model.CardEntity

internal interface CardDao {

    @Insert
    fun insert(card: CardEntity)

    @Query("SELECT * FROM CardEntity WHERE cardBin =:cardBin")
    fun getByBin(cardBin: Int): CardEntity?
}