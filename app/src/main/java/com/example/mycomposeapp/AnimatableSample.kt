package com.example.mycomposeapp

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimatableSamplePreview() {
    MyComposeAppTheme {
        AnimatableSample()
    }
}

@Composable
fun AnimatableSample() {
    Box(Modifier.fillMaxSize()) {
        val isAnimated = remember { mutableStateOf(false) }

        val color = remember { Animatable(Color.DarkGray) }

        // animate to green/red based on `button click`
        LaunchedEffect(isAnimated.value) {// LaunchedEffect is a coroutine
            color.animateTo(
                if (isAnimated.value) Color.Green else Color.Red,
                animationSpec = tween(2000)
            )
        }

        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .background(color.value)
        )
        Button(
            onClick = { isAnimated.value = !isAnimated.value },
            modifier = Modifier.padding(top = 10.dp).align(Alignment.BottomCenter)
        ) {
            Text(text = "Animate Color")
        }
    }
}