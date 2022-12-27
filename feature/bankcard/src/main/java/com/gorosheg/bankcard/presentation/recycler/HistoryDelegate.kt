package com.gorosheg.bankcard.presentation.recycler

import com.gorosheg.android.model.recycler.adapter
import com.gorosheg.android.model.recycler.adapterDelegate
import com.gorosheg.bankcard.databinding.HistoryBlockBinding
import com.gorosheg.bankcard.databinding.ListOfHistoryBinding
import com.gorosheg.bankcard.presentation.model.BankCardItem.History
import com.gorosheg.bankcard.presentation.model.ShortCard

internal fun historyDelegate(
    onItemClick: (cardBin: String) -> Unit,
) = adapterDelegate<History, ListOfHistoryBinding>(ListOfHistoryBinding::inflate) {

    val historyAdapter = historyBlockAdapter(onItemClick)
    historyList.adapter = historyAdapter

    bind {
        historyAdapter.items = item.cards
        historyAdapter.notifyDataSetChanged()
    }
}

private fun historyBlockAdapter(
    onItemClick: (cardBin: String) -> Unit,
) = adapter<ShortCard, HistoryBlockBinding>(HistoryBlockBinding::inflate) {

    root.setOnClickListener {
        onItemClick.invoke(item.cardBin)
    }

    bind {
        scheme.text = item.scheme
        cardBin.text = item.cardBin
    }
}