package com.example.mycomposeapp.screen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme

@Composable
fun ScreenC(navController: NavHostController = rememberNavController(), userName: String = "Default") {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen C", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = userName, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = {
            navController.navigate(Screen.A.route) {
                popUpTo(Screen.A.route) { inclusive = true }
            }
        }) {
            Text("Back To A")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScreenCPreview() {
    MyComposeAppTheme {
        ScreenC()
    }
}