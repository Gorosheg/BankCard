package com.gorosheg.bankcard.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.FragmentActivity

@SuppressLint("IntentReset")
fun FragmentActivity.onBankUrlClicked(url: String) {
    val address = Uri.parse("http://$url")
    val intent = Intent(Intent.ACTION_VIEW, address)
    startActivity(Intent.createChooser(intent, "dialogTitle"))
}

@SuppressLint("IntentReset")
fun FragmentActivity.onBankPhoneClicked(phoneNumber: String) {
    val phone = Uri.parse("tel:$phoneNumber")
    val intent = Intent(Intent.ACTION_CALL, phone)
    startActivity(Intent.createChooser(intent, "dialogTitle"))
}

@SuppressLint("IntentReset")
fun FragmentActivity.onBankCoordinatesClicked(latitude: String, longitude: String) {
    val coordinates = Uri.parse("geo:$latitude,$longitude")
    val intent = Intent(Intent.ACTION_VIEW, coordinates)
    startActivity(Intent.createChooser(intent, "dialogTitle"))
}
