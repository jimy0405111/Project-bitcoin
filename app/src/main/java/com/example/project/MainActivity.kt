package com.example.project



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Data class for Cryptocurrency
data class Crypto(val symbol: String, val price: String)

// Binance API interface
interface BinanceApi {
    @GET("ticker/price")
    suspend fun getCryptoPrices(): List<Crypto>
}

// Main Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleBinanceApp()
        }
    }
}

// Composable Function
@Composable
fun SimpleBinanceApp() {
    var cryptoList by remember { mutableStateOf<List<Crypto>>(emptyList()) }
    val api = remember { createBinanceApi() }

    // Fetch data when the app starts
    LaunchedEffect(Unit) {
        cryptoList = fetchCryptoPrices(api)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Binance Clone") })
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (cryptoList.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                CryptoList(cryptoList)
            }
        }
    }
}

// Function to create Binance API instance
fun createBinanceApi(): BinanceApi {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.binance.com/api/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(BinanceApi::class.java)
}

// Function to fetch cryptocurrency prices
suspend fun fetchCryptoPrices(api: BinanceApi): List<Crypto> {
    return withContext(Dispatchers.IO) {
        try {
            api.getCryptoPrices()
        } catch (e: Exception) {
            emptyList()
        }
    }
}

// Composable to display cryptocurrency list
@Composable
fun CryptoList(cryptoList: List<Crypto>) {
    LazyColumn {
        items(cryptoList.size) { index ->
            val crypto = cryptoList[index]
            CryptoItem(crypto)
        }
    }
}

// Composable to display individual cryptocurrency item
@Composable
fun CryptoItem(crypto: Crypto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = crypto.symbol, style = MaterialTheme.typography.h6)
            Text(text = "$${crypto.price}", style = MaterialTheme.typography.body1)
        }
    }
}
