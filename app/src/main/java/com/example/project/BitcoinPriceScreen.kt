package com.example.project

import androidx.compose.foundation.layout.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BitcoinPriceScreen(viewModel: BitcoinViewModel = viewModel()) {
    val bitcoinPrice by viewModel.bitcoinPrice.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadBitcoinPrice()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bitcoin Price", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (bitcoinPrice != null) {
            Text(
                text = "$${bitcoinPrice.toString()}",
                style = MaterialTheme.typography.headlineLarge
            )
        } else {
            CircularProgressIndicator()
        }
    }
}
