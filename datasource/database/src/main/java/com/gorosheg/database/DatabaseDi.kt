package com.gorosheg.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<DatabaseDatasource> {
        DatabaseDatasourceImpl(get())
    }

    single {
        get<CardDatabase>().cityDao
    }

    single {
        Room.databaseBuilder(androidContext(), CardDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }
}