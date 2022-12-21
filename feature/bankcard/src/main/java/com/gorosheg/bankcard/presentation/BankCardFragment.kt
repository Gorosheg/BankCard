package com.gorosheg.bankcard.presentation

import androidx.fragment.app.Fragment
import com.gorosheg.bankcard.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class BankCardFragment : Fragment(R.layout.fragment_bank_card) {

    private val viewModel: BankCardViewModel by viewModel()

    companion object {
        fun newInstance() = BankCardFragment()
    }
}