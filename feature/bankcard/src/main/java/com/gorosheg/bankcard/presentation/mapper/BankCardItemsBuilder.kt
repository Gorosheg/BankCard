package com.gorosheg.bankcard.presentation.mapper

import com.gorosheg.bankcard.presentation.model.BankCardItem
import com.gorosheg.bankcard.presentation.model.BankCardItem.*
import com.gorosheg.bankcard.presentation.model.Header

internal fun buildHeader(header: Header): BankCardItem.Header = Header(header.title)
