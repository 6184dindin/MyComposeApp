package com.example.mycomposeapp.ui.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

const val ADDRESS_DETAIL_SCREEN_ROUTE = "addressDetail?addressId={addressId}"
const val ARG_ADDRESS_ID = "addressId"

fun getAddressDetailRoute(addressId: String?): String {
    return if (addressId != null) "addressDetail?addressId=$addressId" else "addressDetail"
}

@Composable
fun AddressDetailScreen(addressId: String?, saveAddressAndBack: (String?) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        var savableAddressId by rememberSaveable {
            mutableStateOf(addressId)
        }

        if (addressId.isNullOrEmpty()) {
            Text(text = "Add new Address")
        } else {
            Text(text = "Edit Address with id: $addressId")
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = savableAddressId ?: "", onValueChange = {
            savableAddressId = it
        })
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            saveAddressAndBack(savableAddressId)
        }) {
            Text("Save")
        }
    }

}