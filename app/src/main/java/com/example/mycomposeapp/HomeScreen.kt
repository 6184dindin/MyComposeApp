package com.example.mycomposeapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MyViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    // State của ViewModel
    val counter = mutableStateOf(0)

    val name = savedStateHandle.get<String>("name")
    val age = savedStateHandle.get<Int>("age")

    fun increment() {
        counter.value++
    }
}

@Composable
fun HomeScreenWithViewModel(clickCount: Int) {

    val viewModel = viewModel<MyViewModel>()

    // So sánh với lần trước đó
    if (clickCount != remember { clickCount }) {
        // Xử lý recompose

        Column(Modifier.padding(16.dp)) {
            // Hiển thị click count
            Text(text = "count: $clickCount")
            // Hiển thị viewModel
            Text(text = viewModel.toString())
            // Hiển thị state
            Text(text = viewModel.counter.value.toString())

            // Sự kiện click nút
            Button(onClick = {
                viewModel.increment()
            }) {
                Text("Increment")
            }
        }
    }
}

@Composable
fun HomeScreenWithViewModel2(openNewScreen: () -> Unit = {}) {

    val viewModel = viewModel<MyViewModel>()
    val x = remember {
        mutableStateOf(0)
    }
    val xSaved = rememberSaveable {
        mutableStateOf(0)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(Modifier.padding(16.dp)) {
            // Hiển thị name
            Text(text = "name: ${ viewModel.name.toString() }")
            // Hiển thị age
            Text(text = "age: ${viewModel.age.toString()}")
            // Hiển thị viewModel
            Text(text = viewModel.toString())
            // Hiển thị state
            Text(text = viewModel.counter.value.toString())
            // Hiển thị x
            Text(text = "x = ${x.value}")
            // Hiển thị x saved
            Text(text = "x saved = ${xSaved.value}")

            // Sự kiện click nút
            Button(onClick = {
                viewModel.increment()
                x.value++
            }) {
                Text("Increment")
            }
            // Sự kiện click nút
            Button(onClick = {
                openNewScreen.invoke()
            }) {
                Text("Open new screen")
            }
        }
    }
}
