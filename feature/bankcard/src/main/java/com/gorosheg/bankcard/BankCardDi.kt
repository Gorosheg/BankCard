package com.gorosheg.bankcard

import com.gorosheg.bankcard.data.BankCardRepositoryImpl
import com.gorosheg.bankcard.domain.BankCardRepository
import com.gorosheg.bankcard.presentation.BankCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bankCardModule = module {
    viewModel {
        BankCardViewModel(get())
    }

    factory<BankCardRepository> {
        BankCardRepositoryImpl(get())
    }
}