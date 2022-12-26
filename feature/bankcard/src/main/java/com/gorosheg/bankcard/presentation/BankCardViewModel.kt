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
import com.gorosheg.bankcard.presentation.model.Header
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

internal class BankCardViewModel(private val repository: BankCardRepository) : ViewModel() {

    val state = MutableStateFlow(BankCardViewState())

    init {
        setState("")
    }

    fun searchCard(cardBin: String) {
        setState(cardBin)
    }

    private fun setState(cardBin: String) {
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

    private suspend fun buildState(cardBin: String): List<BankCardItem> {
        return listOf(
            buildHeader(Header.CARD),
            loadCard(cardBin),
            buildHeader(Header.HISTORY),
            getHistory()
        )
    }

    private suspend fun loadCard(cardBin: String): CardUi {
        return repository.getCard(cardBin).mapToUiCard()
    }

    private fun getHistory(): BankCardItem.History {
        return repository.getAllRequestedCards().mapToUiHistory()
    }
}