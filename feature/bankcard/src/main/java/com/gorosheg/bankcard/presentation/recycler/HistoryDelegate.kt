package com.gorosheg.bankcard.presentation.recycler

import com.gorosheg.bankcard.databinding.HistoryBlockBinding
import com.gorosheg.bankcard.presentation.model.BankCardItem

internal fun historyDelegate() =
    adapterDelegate<BankCardItem.History, HistoryBlockBinding>(HistoryBlockBinding::inflate) {
        bind {
            scheme.text = item.scheme
            cardBin.text = item.cardBin
        }
    }