package com.gorosheg.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gorosheg.database.model.CardEntity

@Dao
internal interface CardDao {

    @Insert
    fun insert(card: CardEntity)

    @Query("SELECT * FROM CardEntity WHERE cardBin =:cardBin")
    fun getByBin(cardBin: Int): CardEntity?

    @Query("SELECT * FROM CardEntity")
    fun getAll(): List<CardEntity>?
}