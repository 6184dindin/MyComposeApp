package com.example.mycomposeapp

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TransitionAnimation() {
    var isAnimated by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isAnimated, label = "transition")
    val rocketOffset by transition.animateOffset(transitionSpec = {
        if (this.targetState) {
            tween(1000) // launch duration
        } else {
            tween(1500) // land duration
        }
    }, label = "rocket offset") { animated ->
        if (animated) Offset(0f, 0f) else Offset(0f, 500f)
    }

    val rocketSize by transition.animateDp(transitionSpec = {
        tween(1000)
    }, "rocket size") { animated ->
        if (animated) 80.dp else 160.dp
    }


    Box(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.rocket),
            contentDescription = "Rocket",
            modifier = Modifier
                .size(rocketSize)
                .offset(rocketOffset.x.dp, rocketOffset.y.dp)
        )
        Button(
            onClick = { isAnimated = !isAnimated },
            modifier = Modifier.padding(top = 10.dp).align(Alignment.BottomCenter)
        ) {
            Text(text = if (isAnimated) "Land rocket" else "Launch rocket")
        }
    }
}