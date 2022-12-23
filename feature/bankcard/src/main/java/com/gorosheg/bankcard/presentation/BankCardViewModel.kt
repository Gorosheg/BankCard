package com.gorosheg.bankcard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gorosheg.android.model.BankCard
import com.gorosheg.bankcard.domain.BankCardRepository
import com.gorosheg.bankcard.presentation.model.BankCardItem
import com.gorosheg.bankcard.presentation.model.BankCardItem.Card
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
            getCard(cardBin),
            buildHeader(Header.HISTORY),
            //todo: buildHistory()
        )
    }

    private suspend fun getCard(cardBin: Int): Card {
        return repository.getCardData(cardBin).mapToCard()
    }

    private fun BankCard.mapToCard(): Card {
        return Card(
            scheme = scheme,
            type = type,
            brand = brand,
            prepaid = prepaid.toString(),
            numberLength = cardNumber.length.toString(),
            numberLuhn = cardNumber.luhn.toString(),
            countryCode = country.emoji,
            countryName = country.name,
            countryCoordinates = country.coordinates,
            bankNameAndCity = bank.nameAndCity,
            bankUrl = bank.url,
            bankPhone = bank.phone,
        )
    }
}