package com.gorosheg.bankcard.presentation.recycler

import com.gorosheg.bankcard.databinding.CardBlockBinding
import com.gorosheg.bankcard.presentation.recycler.models.BankCardItems

internal fun cardDelegate() =
    adapterDelegate<BankCardItems.Card, CardBlockBinding>(CardBlockBinding::inflate) {
        bind {
            schemeNetwork.text = item.scheme
            type.text = item.type
            prepaid.text = item.prepaid
            brand.text = item.brand
            length.text = item.numberLength
            luhn.text = item.numberLuhn
            countryCode.text = item.countryCode
            countryName.text = item.countryName
            countryCoordinates.text = item.countryLatitude + item.countryLongitude //TODO
            bankName.text = item.bankName + "" + item.bankCity
            bankUrl.text = item.bankUrl
            bankPhone.text = item.bankPhone
        }
    }