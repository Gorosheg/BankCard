package com.gorosheg.bankcard.presentation.recycler

import com.gorosheg.bankcard.databinding.HeaderBlockBinding
import com.gorosheg.bankcard.presentation.recycler.models.BankCardItems

internal fun headerDelegate() =
    adapterDelegate<BankCardItems.Header, HeaderBlockBinding>(HeaderBlockBinding::inflate) {
        bind {
            title.text = context.getString(item.title)
        }
    }