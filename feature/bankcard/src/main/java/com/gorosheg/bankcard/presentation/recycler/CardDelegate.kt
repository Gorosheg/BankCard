package com.gorosheg.bankcard.presentation.recycler

import android.graphics.Paint
import com.gorosheg.android.model.recycler.adapterDelegate
import com.gorosheg.bankcard.databinding.CardBlockBinding
import com.gorosheg.bankcard.presentation.model.BankCardItem

internal fun cardDelegate(
    onBankUrlClick: (url: String) -> Unit,
    onBankPhoneClick: (phone: String) -> Unit,
    onBankAddressClick: (latitude: String, longitude: String) -> Unit,
) = adapterDelegate<BankCardItem.CardUi, CardBlockBinding>(CardBlockBinding::inflate) {

    bind {
        schemeNetwork.text = item.scheme
        type.text = item.type
        prepaid.text = item.isPrepaid
        brand.text = item.brand
        length.text = item.numberLength
        luhn.text = item.numberLuhn

        countryCode.text = item.countryCode
        countryName.text = item.countryName
        countryCoordinates.text = "(latitude: ${item.latitude}, longitude:${item.longitude})"

        bankName.text = item.bankNameAndCity
        bankUrl.text = item.bankUrl
        bankPhone.text = item.bankPhone

        bankPhone.paintFlags = bankPhone.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        countryCoordinates.paintFlags = bankPhone.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    bankUrl.setOnClickListener { onBankUrlClick.invoke(bankUrl.text.toString()) }
    bankPhone.setOnClickListener { onBankPhoneClick.invoke(bankPhone.text.toString()) }
    countryCoordinates.setOnClickListener { onBankAddressClick.invoke(item.latitude, item.longitude) }
}