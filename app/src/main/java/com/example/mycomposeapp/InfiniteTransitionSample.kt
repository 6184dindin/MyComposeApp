package com.example.mycomposeapp

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InfiniteAnimation() {
    MyComposeAppTheme {
        val infiniteTransition = rememberInfiniteTransition()

        val heartSize by infiniteTransition.animateFloat(
            initialValue = 100.0f,
            targetValue = 250.0f,
            animationSpec = infiniteRepeatable(
                animation = tween(800, delayMillis = 100,easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(R.drawable.heart),

                contentDescription = "heart",
                modifier = Modifier
                    .size(heartSize.dp)
            )
        }
    }
}