package com.gorosheg.bankcard.presentation.model

internal data class BankCardViewState(
    val items: List<BankCardItem> = emptyList(),
    val isCardEmpty: Boolean = false,
)