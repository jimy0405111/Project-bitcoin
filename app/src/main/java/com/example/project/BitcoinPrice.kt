package com.example.project

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BitcoinRepository {
    private val api = ApiClient.instance

    suspend fun fetchBitcoinPrice(): Double {
        return withContext(Dispatchers.IO) {
            api.getBitcoinPrice().bitcoin.usd
        }
    }
}
