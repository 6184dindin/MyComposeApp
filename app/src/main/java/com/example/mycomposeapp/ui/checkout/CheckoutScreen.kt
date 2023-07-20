package com.example.mycomposeapp.ui.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

const val CHECKOUT_SCREEN_ROUTE = "checkout/{cartId}/{customerId}"
const val ARG_CART_ID = "cartId"
const val ARG_CUSTOMER_ID = "customerId"

fun getCheckoutRoute(cartId: String, customerId: String): String {
    return "checkout/$cartId/$customerId"
}

@Composable
fun CheckoutScreen(
    cartId: String, customerId: String,
    placeOrderAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text("Processing cart with id: $cartId for customer with id: $customerId")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            placeOrderAction()
        }) {
            Text(text = "Place order")
        }
    }
}
