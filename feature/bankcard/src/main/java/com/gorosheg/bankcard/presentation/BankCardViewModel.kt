package com.gorosheg.bankcard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorosheg.bankcard.domain.BankCardRepository
import com.gorosheg.bankcard.presentation.mapper.buildHeader
import com.gorosheg.bankcard.presentation.mapper.mapToUiCard
import com.gorosheg.bankcard.presentation.mapper.mapToUiHistory
import com.gorosheg.bankcard.presentation.model.BankCardItem
import com.gorosheg.bankcard.presentation.model.BankCardItem.CardUi
import com.gorosheg.bankcard.presentation.model.BankCardViewState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class BankCardViewModel(private val repository: BankCardRepository) : ViewModel() {

    val state = MutableStateFlow(BankCardViewState())

    init {
        setState(45717360)
    }

    fun searchCard(cardBin: Int) {
        setState(cardBin)
    }

    private fun setState(cardBin: Int) {
        viewModelScope.launch {
            try {
                val adapterItems = buildState(cardBin)
                state.value = BankCardViewState(adapterItems)
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                e.printStackTrace()
            }
        }
    }

    private suspend fun buildState(cardBin: Int): List<BankCardItem> {
        return listOf(
            buildHeader(Header.CARD),
            loadCard(cardBin),
            buildHeader(Header.HISTORY),
            getHistory()
        )
    }

    private suspend fun loadCard(cardBin: Int): CardUi {
        return repository.getCard(cardBin).mapToUiCard()
    }

    private fun getHistory(): BankCardItem.History {
        return repository.getAllRequestedCards().mapToUiHistory()
    }
}