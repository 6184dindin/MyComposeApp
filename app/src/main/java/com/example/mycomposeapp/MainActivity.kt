package com.example.mycomposeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeAppTheme {
                // A surface container using the 'background' color from the theme
                CompositionLocalProvider(
                    LocalTestColor provides TestColor()
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MainScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Log.e("TAG", "MainScreen: ")
    var testColorA by remember { mutableStateOf(getTestColor()) }
    Column(Modifier.padding(16.dp)) {
        Header()
        Body(content = "Changeable text color", testColor = testColorA)
        Button(onClick = {
            testColorA = getTestColor()
        }) {
            Text(text = "Change Color")
        }
    }
}

@Composable
fun Header(title: String = "Header") {
    Log.e("TAG", "Header: ")
    Column {
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun Body(content: String = "Body", testColor: Color) {
    Log.e("TAG", "Body: ")
    CompositionLocalProvider(
        LocalTestColor provides LocalTestColor.current.copy(color = testColor)
    ) {
        Column {
            Text(text = content, style = TextStyle(color = LocalTestColor.current.color))
            CheckImage()
        }
    }
}

@Composable
fun CheckImage() {
    Log.e("TAG", "CheckImage: ")
    Column {
        Text(text = "CheckImage", style = MaterialTheme.typography.bodyLarge)
    }
}

data class TestColor(val color: Color = Color.Black)

val LocalTestColor = compositionLocalOf { TestColor(Color.Black) }
//val LocalTestColor = staticCompositionLocalOf { TestColor(Color.Black) }

fun getTestColor(): Color {
    return listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow).random()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TestPreview() {
    MyComposeAppTheme {
        MainScreen()
    }
}