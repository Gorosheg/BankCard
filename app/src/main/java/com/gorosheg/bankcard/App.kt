package com.gorosheg.bankcard

import android.app.Application
import com.gorosheg.bankcard.navigation.navigatorModule
import com.gorosheg.database.databaseModule
import com.gorosheg.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                navigatorModule,
                bankCardModule,
                networkModule,
                databaseModule
            )
        }
    }
}