package com.example.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BitcoinViewModel : ViewModel() {
    private val repository = BitcoinRepository()

    private val _bitcoinPrice = MutableStateFlow<Double?>(null)
    val bitcoinPrice: StateFlow<Double?> get() = _bitcoinPrice

    fun loadBitcoinPrice() {
        viewModelScope.launch {
            try {
                val price = repository.fetchBitcoinPrice()
                _bitcoinPrice.value = price
            } catch (e: Exception) {
                _bitcoinPrice.value = null // Handle error
            }
        }
    }
}
