package com.gorosheg.bankcard.presentation.recycler

import com.gorosheg.bankcard.databinding.HeaderBlockBinding
import com.gorosheg.bankcard.presentation.models.BankCardItem

internal fun headerDelegate() =
    adapterDelegate<BankCardItem.Header, HeaderBlockBinding>(HeaderBlockBinding::inflate) {
        bind {
            title.text = context.getString(item.title)
        }
    }