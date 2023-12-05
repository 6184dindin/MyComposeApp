package com.example.mycomposeapp.ui.customer

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize


@Parcelize
data class Address(
    val id: Int = 0,
    val street: String = "",
    val city: String = ""
) : Parcelable {

    override fun toString(): String {
        return gson.toJson(this)
    }

    companion object {
        val gson = Gson()

        fun from(value: String) = gson.fromJson(value, Address::class.java) ?: Address()
    }
}

class AddressNavType : NavType<Address>(isNullableAllowed = true) {

    override fun get(bundle: Bundle, key: String): Address? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Address {
        return Address.from(value)
    }

    override fun put(bundle: Bundle, key: String, value: Address) {
        bundle.putParcelable(key, value)
    }
}


object AddressNavigation {

    const val addressArg = "parametersArg"
    const val route = "addressBook?$addressArg={$addressArg}"

    fun createRoute(addresses: Address): String {
        val encoded = Uri.encode(addresses.toString())
        return "addressBook?$addressArg=$encoded"
    }

    fun fromNav(navBackStackEntry: NavBackStackEntry): Address? {
        return navBackStackEntry.arguments?.getParcelable(addressArg)
    }

}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddressScreen(addresses: List<Address?>, onBack: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar {
                Text(
                    text = "AddressListScreen",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(24.dp)
        ) {
            items(addresses) { address ->
                Text(text = address.toString())
            }
        }

    }

    // Handle back button press
    BackHandler() {
        onBack()
    }
}