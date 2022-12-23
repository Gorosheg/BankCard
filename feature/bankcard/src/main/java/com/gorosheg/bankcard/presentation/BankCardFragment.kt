package com.gorosheg.bankcard.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gorosheg.bankcard.R
import com.gorosheg.bankcard.databinding.FragmentBankCardBinding
import com.gorosheg.bankcard.presentation.model.BankCardViewState
import com.gorosheg.bankcard.presentation.recycler.CommonAdapter
import com.gorosheg.bankcard.presentation.recycler.cardDelegate
import com.gorosheg.bankcard.presentation.recycler.headerDelegate
import com.gorosheg.bankcard.presentation.recycler.historyDelegate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class BankCardFragment : Fragment(R.layout.fragment_bank_card) {

    private val viewModel: BankCardViewModel by viewModel()
    private val binding: FragmentBankCardBinding by viewBinding()

    private val adapter = CommonAdapter(
        headerDelegate(),
        historyDelegate(),
        cardDelegate()
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        viewModel.state.onEach(::render).launchIn(lifecycleScope)
        bankCardMainRecycler.adapter = adapter

        searchIcon.setOnClickListener {
            val cardBin = searchBin.text.toString().toInt()
            viewModel.searchCard(cardBin)
        }
    }

    private fun render(state: BankCardViewState) {
        adapter.items = state.items
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = BankCardFragment()
    }
}