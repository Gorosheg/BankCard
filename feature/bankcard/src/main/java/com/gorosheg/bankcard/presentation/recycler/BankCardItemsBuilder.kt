package com.gorosheg.bankcard.presentation.recycler

import com.gorosheg.bankcard.presentation.Header
import com.gorosheg.bankcard.presentation.models.BankCardItem

internal fun buildHeader(header: Header): BankCardItem.Header {
    return BankCardItem.Header(header.title)
}

