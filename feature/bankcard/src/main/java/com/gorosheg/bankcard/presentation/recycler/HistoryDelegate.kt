package com.gorosheg.bankcard.presentation.recycler

import com.gorosheg.android.model.recycler.adapter
import com.gorosheg.android.model.recycler.adapterDelegate
import com.gorosheg.bankcard.databinding.HistoryBlockBinding
import com.gorosheg.bankcard.databinding.ListOfHistoryBinding
import com.gorosheg.bankcard.presentation.model.BankCardItem.History
import com.gorosheg.bankcard.presentation.model.ShortCard

internal fun historyDelegate() =
    adapterDelegate<History, ListOfHistoryBinding>(ListOfHistoryBinding::inflate) {
        val historyAdapter = historyBlockAdapter()
        historyList.adapter = historyAdapter

        bind {
            historyAdapter.items = item.cards
            historyAdapter.notifyDataSetChanged()
        }
    }

internal fun historyBlockAdapter() = adapter<ShortCard, HistoryBlockBinding>(HistoryBlockBinding::inflate) {

    bind {
        scheme.text = item.scheme
        cardBin.text = item.cardBin
    }
}