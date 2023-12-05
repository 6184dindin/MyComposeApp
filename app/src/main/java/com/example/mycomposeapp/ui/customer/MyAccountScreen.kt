@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mycomposeapp.ui.customer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycomposeapp.base.GetResultOneTime
import com.example.mycomposeapp.ui.ArgumentKeys


@Composable
fun MyAccountScreen(navController: NavController, openAddressScreen: (String?) -> Unit) {
    var addressId by rememberSaveable {
        mutableStateOf("")
    }

    navController.GetResultOneTime<String>(ArgumentKeys.NEW_ADDRESS_ID) {
        addressId = it ?: return@GetResultOneTime
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text("My Account ")
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            openAddressScreen(null)
        }) {
            Text("Add address")
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = addressId, onValueChange = {
            addressId = it
        })
        Spacer(modifier = Modifier.height(6.dp))
        Button(onClick = {
            openAddressScreen(addressId)
        }) {
            Text("Edit Address")
        }


    }

}