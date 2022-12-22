package com.gorosheg.bankcard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorosheg.bankcard.domain.BankCardRepository
import com.gorosheg.bankcard.presentation.models.BankCardItem
import com.gorosheg.bankcard.presentation.models.BankCardViewState
import com.gorosheg.bankcard.presentation.recycler.buildHeader
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class BankCardViewModel(private val repository: BankCardRepository) : ViewModel() {

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

    private suspend fun buildState(): List<BankCardItem> {
        return listOf(
            buildHeader(Header.CARD),
            //getCard()
            buildHeader(Header.HISTORY),
            //todo: buildHistory()
        )
    }

    private suspend fun getCard() {
        return repository.getCardData()
    }
}