package com.gorosheg.bankcard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gorosheg.bankcard.navigation.Navigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createBankCardFragment()
    }

    private fun createBankCardFragment() {
        navigator.navigateToBankCard(this)
    }
}