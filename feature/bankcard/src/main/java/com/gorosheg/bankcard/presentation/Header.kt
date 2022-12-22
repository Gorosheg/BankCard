package com.gorosheg.bankcard.presentation

import androidx.annotation.StringRes
import com.gorosheg.bankcard.R

enum class Header(@StringRes val title: Int) {

    CARD(R.string.card),
    HISTORY(R.string.history),
}