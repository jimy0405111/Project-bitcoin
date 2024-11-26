package com.example.project

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BitcoinApiService {
    @GET("simple/price?ids=bitcoin&vs_currencies=usd")
    suspend fun getBitcoinPrice(): BitcoinPriceResponse
}

data class BitcoinPriceResponse(val bitcoin: BitcoinPrice)
data class BitcoinPrice(val usd: Double)

object ApiClient {
    private const val BASE_URL = "https://api.coingecko.com/api/v3/"

    val instance: BitcoinApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BitcoinApiService::class.java)
    }
}
