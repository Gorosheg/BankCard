package com.gorosheg.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gorosheg.database.model.CardEntity

@Database(entities = [CardEntity::class], version = 1)
internal abstract class CardDatabase : RoomDatabase() {
    abstract val cityDao: CardDao
}