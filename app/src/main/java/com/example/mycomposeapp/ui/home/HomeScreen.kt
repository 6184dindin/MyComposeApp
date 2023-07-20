package com.example.mycomposeapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

const val HOME_SCREEN_ROUTE = "home"

@Composable
fun HomeScreen(
    openCategoryAction: () -> Unit,
    openMyAccountScreen: () -> Unit,
    editCustomerInfo: () -> Unit,
    openAddressList: () -> Unit
) {
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