package com.gorosheg.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class CardNumberResponse(
    @SerializedName("length")
    val length: Int,
    @SerializedName("luhn")
    val luhn: Boolean,
)