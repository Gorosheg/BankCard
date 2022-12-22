package com.gorosheg.bankcard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorosheg.bankcard.domain.BankCardInteractor
import com.gorosheg.bankcard.presentation.models.BankCardItem
import com.gorosheg.bankcard.presentation.models.BankCardViewState
import com.gorosheg.bankcard.presentation.recycler.buildHeader
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class BankCardViewModel(private val interactor: BankCardInteractor) : ViewModel() {

    val state = MutableStateFlow(BankCardViewState())

    init {
        setState()
    }

    private fun setState() {
        viewModelScope.launch {
            try {
                val adapterItems = buildState()
                state.value = BankCardViewState(adapterItems)
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                e.printStackTrace()
            }
        }
    }

    private fun buildState(): List<BankCardItem> {
        return listOf(
            buildHeader(Header.CARD),
            //todo: buildCard()
            buildHeader(Header.HISTORY),
            //todo: buildHistory()
        )
    }
}