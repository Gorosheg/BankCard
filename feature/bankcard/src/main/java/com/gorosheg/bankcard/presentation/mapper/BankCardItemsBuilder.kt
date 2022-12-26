package com.gorosheg.bankcard.presentation.mapper

import com.gorosheg.bankcard.presentation.Header
import com.gorosheg.bankcard.presentation.model.BankCardItem
import com.gorosheg.bankcard.presentation.model.BankCardItem.*

internal fun buildHeader(header: Header): BankCardItem.Header = Header(header.title)
