package com.example.mycomposeapp.ui.catalog.category

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

const val CATEGORY_SCREEN_ROUTE = "category_screen_route"
fun getOpenCategoryScreenRoute() = CATEGORY_SCREEN_ROUTE

@Composable
fun CategoryScreen(openProductDetail: (String) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for lifecycle events
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    Log.d("CategoryScreen", "ON_CREATE")
                }
                Lifecycle.Event.ON_START -> {
                    Log.d("CategoryScreen", "ON_START")
                }
                Lifecycle.Event.ON_RESUME -> {
                    Log.d("CategoryScreen", "ON_RESUME")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.d("CategoryScreen", "ON_PAUSE")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.d("CategoryScreen", "ON_STOP")
                }
                Lifecycle.Event.ON_DESTROY -> {
                    Log.d("CategoryScreen", "ON_DESTROY")
                }
                else -> {}
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            Log.d("CategoryScreen", "composition EXIT")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(text = "Category Screen")
        Spacer(modifier = Modifier.height(24.dp))

        var productId by rememberSaveable {
            mutableStateOf("")
        }
        OutlinedTextField(value = productId, onValueChange = {
            productId = it
        }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { openProductDetail(productId) }) {
            Text("Open Product Detail")
        }
    }
}
