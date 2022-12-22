package com.gorosheg.bankcard.presentation

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gorosheg.bankcard.R
import com.gorosheg.bankcard.databinding.FragmentBankCardBinding
import com.gorosheg.bankcard.presentation.recycler.CommonAdapter
import com.gorosheg.bankcard.presentation.recycler.cardDelegate
import com.gorosheg.bankcard.presentation.recycler.headerDelegate
import com.gorosheg.bankcard.presentation.recycler.historyDelegate
import org.koin.androidx.viewmodel.ext.android.viewModel

class BankCardFragment : Fragment(R.layout.fragment_bank_card) {

    private val viewModel: BankCardViewModel by viewModel()
    private val binding: FragmentBankCardBinding by viewBinding()

    private val adapter = CommonAdapter(
        headerDelegate(),
        historyDelegate(),
        cardDelegate()
    )

    companion object {
        fun newInstance() = BankCardFragment()
    }
}