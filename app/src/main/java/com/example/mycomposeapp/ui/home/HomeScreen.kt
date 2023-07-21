package com.example.mycomposeapp.ui.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

const val HOME_SCREEN_ROUTE = "home"

@Composable
fun HomeScreen(
    openCategoryAction: () -> Unit,
    openMyAccountScreen: () -> Unit,
    editCustomerInfo: () -> Unit,
    openAddressList: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for lifecycle events
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    Log.d("HomeScreen", "ON_CREATE")
                }
                Lifecycle.Event.ON_START -> {
                    Log.d("HomeScreen", "ON_START")
                }
                Lifecycle.Event.ON_RESUME -> {
                    Log.d("HomeScreen", "ON_RESUME")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.d("HomeScreen", "ON_PAUSE")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.d("HomeScreen", "ON_STOP")
                }
                Lifecycle.Event.ON_DESTROY -> {
                    Log.d("HomeScreen", "ON_DESTROY")
                }
                else -> {}
            }
        }

        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            Log.d("HomeScreen", "composition EXIT")
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { openCategoryAction() }) {
            Text("Open Category")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            openMyAccountScreen()
        }) {
            Text(text = "Open MyAccount")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            editCustomerInfo()
        }) {
            Text(text = "Edit customer information")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            openAddressList()
        }) {
            Text("Address List")
        }
    }
}