package com.gorosheg.bankcard.presentation.recycler

import com.gorosheg.android.model.recycler.adapterDelegate
import com.gorosheg.bankcard.databinding.HeaderBlockBinding
import com.gorosheg.bankcard.presentation.model.BankCardItem.Header

internal fun headerDelegate() = adapterDelegate<Header, HeaderBlockBinding>(HeaderBlockBinding::inflate) {

    bind {
        title.text = context.getString(item.title)
    }
}