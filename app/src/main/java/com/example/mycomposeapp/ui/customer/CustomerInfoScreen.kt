package com.example.mycomposeapp.ui.customer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

const val CUSTOMER_INFO_SCREEN_ROUTE = "customerInfo"

@Composable
fun CustomerInfoScreen(onClickBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(text = "Customer Information")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            onClickBack()
        }) {
            Text("Back")
        }
    }
}
