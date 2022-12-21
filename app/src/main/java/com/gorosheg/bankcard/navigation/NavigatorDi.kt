package com.gorosheg.bankcard.navigation

import org.koin.dsl.module

val navigatorModule = module {
    single<Navigator> { get<NavigatorImpl>() }
    single { NavigatorImpl() }
}