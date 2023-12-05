package com.example.mycomposeapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycomposeapp.ui.Screen
import com.example.mycomposeapp.ui.customer.Address
import com.example.mycomposeapp.ui.customer.AddressNavigation
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen", fontSize = 48.sp)
        Spacer(modifier = Modifier.height(64.dp))
        Button(
            onClick = {
                navController.navigate(Screen.Category.route)
            }
        ) {
            Text("Open Category")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                navController.navigate(Screen.MyAccount.route)
            }
        ) {
            Text(text = "Open MyAccount")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                navController.navigate(Screen.Customer.route)
            }
        ) {
            Text(text = "Edit customer information")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                val address = Address(id = 1, street = "Phạm Hùng", city = "Hà Nội")
                navController.navigate(AddressNavigation.createRoute(address))
            }
        ) {
            Text("Address List")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TestPreview() {
    MyComposeAppTheme {
        HomeScreen(navController = rememberNavController())
    }
}