package com.example.mycomposeapp.ui.catalog.product

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

const val PRODUCT_DETAIL_SCREEN_ROUTE = "product?productId={productId}"
const val ARG_PRODUCT_ID = "productId"

fun getProductDetailRoute(productId: String?): String {
    return if (productId != null)
        "product?productId=$productId"
    else
        "product"
}

@Composable
fun ProductDetailScreen(productId: String, checkout: (String, String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Product with id : $productId")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            checkout("224", "9988")
        }) {
            Text("Checkout")
        }
    }

}