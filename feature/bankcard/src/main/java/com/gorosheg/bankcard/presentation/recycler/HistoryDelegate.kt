package com.gorosheg.bankcard.presentation.recycler

import com.gorosheg.bankcard.databinding.HistoryBlockBinding
import com.gorosheg.bankcard.presentation.recycler.models.BankCardItems

internal fun historyDelegate() =
    adapterDelegate<BankCardItems.History, HistoryBlockBinding>(HistoryBlockBinding::inflate) {
        bind {
            scheme.text = item.scheme
            cardBin.text = item.cardBin
        }
    }