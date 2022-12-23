package com.gorosheg.bankcard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorosheg.android.model.BankCard
import com.gorosheg.bankcard.domain.BankCardRepository
import com.gorosheg.bankcard.presentation.model.BankCardItem
import com.gorosheg.bankcard.presentation.model.BankCardItem.CardUi
import com.gorosheg.bankcard.presentation.model.BankCardViewState
import com.gorosheg.bankcard.presentation.recycler.buildHeader
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
            //todo: buildHistory()
        )
    }

    private suspend fun loadCard(cardBin: Int): CardUi {
        return repository.getCard(cardBin).mapToCard()
    }

    private fun BankCard.mapToCard(): CardUi {
        return CardUi(
            scheme = scheme,
            type = type,
            brand = brand,
            isPrepaid = prepaid.toString(),
            numberLength = cardNumber.length.toString(),
            numberLuhn = cardNumber.withLuhnAlgorithm.toString(),
            countryCode = country.emoji,
            countryName = country.name,
            countryCoordinates = country.coordinates,
            bankNameAndCity = bank.nameAndCity,
            bankUrl = bank.url,
            bankPhone = bank.phone,
        )
    }
}