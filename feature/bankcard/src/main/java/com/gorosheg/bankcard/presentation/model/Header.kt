package com.gorosheg.bankcard.presentation.model

import androidx.annotation.StringRes
import com.gorosheg.bankcard.R

internal enum class Header(@StringRes val title: Int) {

    CARD(R.string.card),
    HISTORY(R.string.history),
}