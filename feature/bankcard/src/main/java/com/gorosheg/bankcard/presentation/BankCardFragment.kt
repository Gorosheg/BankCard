package com.gorosheg.bankcard.presentation

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gorosheg.android.model.recycler.CommonAdapter
import com.gorosheg.bankcard.R
import com.gorosheg.bankcard.databinding.FragmentBankCardBinding
import com.gorosheg.bankcard.presentation.model.BankCardViewState
import com.gorosheg.bankcard.presentation.recycler.cardDelegate
import com.gorosheg.bankcard.presentation.recycler.headerDelegate
import com.gorosheg.bankcard.presentation.recycler.historyDelegate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class BankCardFragment : Fragment(R.layout.fragment_bank_card) {

    private val viewModel: BankCardViewModel by viewModel()
    private val binding: FragmentBankCardBinding by viewBinding()

    private val adapter by lazy {
        CommonAdapter(
            headerDelegate(),
            historyDelegate(viewModel::searchCard),
            cardDelegate(
                onBankUrlClick = requireActivity()::onBankUrlClicked,
                onBankPhoneClick = requireActivity()::onBankPhoneClicked,
                onBankAddressClick = requireActivity()::onBankCoordinatesClicked
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        viewModel.state.onEach(::render).launchIn(lifecycleScope)
        bankCardMainRecycler.adapter = adapter

        searchIcon.setOnClickListener {
            searchCard()
        }

        searchBin.setOnKeyListener { _, keyCode, event ->
            searchCard(event, keyCode)
        }
    }

    private fun render(state: BankCardViewState) = with(binding) {
        adapter.items = state.items
        adapter.notifyDataSetChanged()

        if (state.isCardEmpty) {
            enterBinHint.isVisible = true
            enterBinHint.text = getString(R.string.enter_bin_hint)
        } else {
            enterBinHint.isVisible = false
        }
    }

    private fun searchCard(event: KeyEvent, keyCode: Int): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN &&
            keyCode == KeyEvent.KEYCODE_ENTER
        ) {
            searchCard()
            return true
        }

        return false
    }

    private fun searchCard() = with(binding) {
        val cardBin = searchBin.text.toString()

        if (cardBin.isEmpty()) {
            enterBinHint.isVisible = true
            enterBinHint.text = getString(R.string.enter_bin_hint)
        } else {
            viewModel.searchCard(cardBin)
        }
    }

    companion object {
        fun newInstance() = BankCardFragment()
    }
}