package com.gorosheg.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class BankCardResponse(
    @SerializedName("number")
    val cardNumber: CardNumberResponse,
    @SerializedName("scheme")
    val scheme: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("prepaid")
    val prepaid: Boolean,
    @SerializedName("country")
    val country: CountryResponse,
    @SerializedName("bank")
    val bank: BankResponse,
)