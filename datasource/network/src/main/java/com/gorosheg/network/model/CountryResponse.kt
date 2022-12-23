package com.gorosheg.network.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class CountryResponse(
    @SerializedName("alpha2")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("latitude")
    val latitude: Int,
    @SerializedName("longitude")
    val longitude: Int,
)