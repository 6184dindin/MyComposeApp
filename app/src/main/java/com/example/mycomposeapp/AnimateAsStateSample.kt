package com.example.mycomposeapp

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimateAsStateSamplePreview() {
    MyComposeAppTheme {
        AnimateAsFloatContent()
    }
}

@Composable
fun CircleImage(imageSize: Dp) {
    Image(
        painter = painterResource(R.drawable.cat),
        contentDescription = "Circle Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(imageSize)
            .clip(CircleShape)
            .border(1.dp, Color.Gray, CircleShape)
    )
}

@Composable
fun AnimateDpAsState() {
    Box(Modifier.fillMaxSize()) {
        val isNeedExpansion = rememberSaveable { mutableStateOf(false) }

        val animatedSizeDp: Dp by animateDpAsState(targetValue = if (isNeedExpansion.value) 350.dp else 100.dp)

        Box(Modifier.align(Alignment.Center)) {
            CircleImage(animatedSizeDp)
        }
        Button(
            onClick = {
                isNeedExpansion.value = !isNeedExpansion.value
            },
            modifier = Modifier
                .padding(top = 50.dp)
                .width(300.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(text = "AnimateDpAsState")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
        }
    }
}

@Composable
fun AnimateAsFloatContent() {
    val isRotated = rememberSaveable { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isRotated.value) 360f else 0f,
        animationSpec = repeatable(
            5,
            animation = tween(2000)
        )
    )
    Box(Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(Alignment.Center)) {
            Image(
                painter = painterResource(R.drawable.fan),
                contentDescription = "Fan",
                modifier = Modifier
                    .size(300.dp)
                    .rotate(rotationAngle)
            )
        }

        Button(
            onClick = { isRotated.value = !isRotated.value },
            modifier = Modifier
                .padding(top = 50.dp)
                .width(200.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(text = "Rotate Fan")
        }
    }
}