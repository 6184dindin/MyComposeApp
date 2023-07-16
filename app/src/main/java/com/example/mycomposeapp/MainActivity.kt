package com.example.mycomposeapp

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.enableLiveLiterals
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(Modifier.padding(16.dp)) {
//        TextSample()
//        ImageSample()
//        ButtonSample()
//        ClickableSample()
//        DetectTapGesturesSample()
//        CheckboxSample()
    }
}

@Composable
fun CheckboxSample() {
    var isSelected by remember {
        mutableStateOf(false)
    }

    Row(Modifier.selectable(
        selected = isSelected,
        role = Role.Checkbox,
        onClick = {
            isSelected = !isSelected
        }
    )) {
        Checkbox(checked = isSelected, onCheckedChange = null,
        colors = CheckboxDefaults.colors(
            checkedColor = Color.Red,
            uncheckedColor = Color.Black
        ))
        Text(text = "Select me")
    }
}

@Composable
fun ClickableSample() {
    var count by remember {
        mutableStateOf(0)
    }
    Text(text = "click count : $count")
    Row(modifier = Modifier.clickable {
        count++
    }) {
        Icon(Icons.Default.Add, contentDescription = null)
        Text(text = "Test click")
    }
}

@Composable
fun DetectTapGesturesSample() {

    val content = remember {
        mutableStateOf("")
    }
    Column {
        Text(text = content.value)
        Text(text = "Click test tap", Modifier.pointerInput(Unit) {
            detectTapGestures(
                onDoubleTap = {
                    content.value = "onDoubleTap"
                },
                onLongPress = {
                    content.value = "onLongPress"
                },
                onPress = {
                    content.value = "onPress"
                },
                onTap = {
                    content.value = "onTap"
                }
            )
        })
    }
}

@Composable
fun ButtonSample() {
    val count = remember {
        mutableStateOf(0)
    }
    Text(text = "click count : ${count.value}")
    Button(
        onClick = {
            count.value++
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.Black
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Image(Icons.Default.Phone, contentDescription = "")
        Space()
        Text(
            text = "Call me",
            fontSize = 24.sp
        )
    }
    Space()
    IconButton(onClick = {}) {
        Icon(Icons.Default.Phone, contentDescription = "")
    }
}

@Composable
fun ImageSample() {
    Image(
        painter = painterResource(id = R.drawable.panda),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(10.dp)
            .clip(shape = CircleShape)
            .border(width = 2.dp, shape = CircleShape, color = Color.Black)
    )
}

@Composable
fun TextSample() {
    Text(text = "Hello Dine!")
    Text(
        text = stringResource(id = R.string.test_text),
        modifier = Modifier.padding(15.dp),
        color = colorResource(id = R.color.purple_200),
        fontSize = 23.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Start,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        textDecoration = TextDecoration.Underline
    )
    MultiStyleText()
}

@Composable
fun MultiStyleText() {
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Blue, fontSize = 25.sp)) {
            append("He")
        }
        withStyle(style = SpanStyle(color = Color.Red)) {
            append("llo")
        }
    }
    )
}

@Composable
fun Space() {
    Spacer(
        modifier = Modifier
            .height(16.dp)
            .width(16.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TestPreview() {
    MyComposeAppTheme {
        HomeScreen()
    }
}