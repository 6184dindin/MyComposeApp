@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mycomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(Modifier.padding(16.dp)) {
        TextSample()
        ImageSample()
//        ButtonSample()
//        ClickableSample()
//        DetectTapGesturesSample()
//        CheckboxSample()
//        TextFieldSample()
//        OutlineTextFieldSample()
//        RowSample()
//        ConstraintLayoutSample()
//        ListSample()
//        StateSample()
    }
}

@Composable
fun StateSample() {
    //state full
    Column {
        Welcome()
        Spacer(modifier = Modifier.height(15.dp))
//        var name by remember {
//            mutableStateOf("")
//        }
        var name by rememberSaveable {
            mutableStateOf("")
        }
        InputName(name) {
            name = it
        }
    }
}

//state less
@Composable
fun InputName(name: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(value = name, onValueChange = onValueChange)
}

//state less
@Composable
fun Welcome() {
    Text(text = "Hello")
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListSample() {
    val listUser = getListUser()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        stickyHeader {
            Text(
                text = "Header",
                Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
                    .padding(10.dp)
            )
        }
        items(listUser) {
            ItemUser(it)
        }
    }
}

@Composable
fun ItemUser(item: User) {
    Row(
        Modifier
            .background(Color.Green)
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Text(text = item.name)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "${item.age}")
    }
}

class User(val name: String, val age: Int)

fun getListUser(): List<User> {
    val result = mutableListOf<User>()
    for (id in 0..30) {
        result.add(User("User $id", id))
    }
    return result
}

//need to add this in build.gradle (app)
@Composable
fun ConstraintLayoutSample() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        val (redBox, greenBox, blueBox) = createRefs()
        Box(
            modifier = Modifier
                .size(100.dp, 100.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )
        Box(
            modifier = Modifier
                .size(100.dp, 100.dp)
                .background(Color.Green)
                .constrainAs(greenBox) {
                    top.linkTo(redBox.bottom, margin = 16.dp)
                    start.linkTo(redBox.end, margin = 16.dp)
                }
        )
        Box(
            modifier = Modifier
                .size(100.dp, 100.dp)
                .background(Color.Blue)
                .constrainAs(blueBox) {
                    top.linkTo(greenBox.bottom, margin = 16.dp)
                    start.linkTo(greenBox.end, margin = 16.dp)
                }
        )

        val guideline = createGuidelineFromTop(fraction = 0.5f)
        val yellowBox = createRef()
        Box(
            modifier = Modifier
                .size(100.dp, 100.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                    top.linkTo(guideline)
                    start.linkTo(parent.start)
                }
        )

        val barrier = createBottomBarrier(blueBox, yellowBox)
        val blackBox = createRef()
        Box(
            modifier = Modifier
                .background(Color.Black)
                .constrainAs(blackBox) {
                    top.linkTo(barrier)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .aspectRatio(3f)
        )
    }
}

@Composable
fun RowSample() {
    Box(
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly, // horizontal: start, end, center, spaceBetween, spaceEvenly, spaceAround
            modifier = Modifier
                .size(350.dp, 150.dp)
                .background(Color.Gray),
            verticalAlignment = Alignment.Top,
        ) {
            ItemBox(Color.Red)
            ItemBox(Color.Green)
            ItemBox(Color.Blue)
        }
    }
    Box(
    ) {
        Column(
            modifier = Modifier
                .size(350.dp, 300.dp)
                .background(Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly // vertical: top, bottom, center, spaceBetween, spaceEvenly, spaceAround
        ) {
            ItemBox(Color.Red)
            ItemBox(Color.Green)
            ItemBox(Color.Blue)
        }
    }
    Box(
    ) {
        Row(
            modifier = Modifier
                .size(350.dp, 150.dp)
                .background(Color.Gray)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ItemBox(Color.Red)
            ItemBox(Color.Green)
            ItemBox(Color.Blue)
            ItemBox(Color.Red)
            ItemBox(Color.Green)
            ItemBox(Color.Blue)
            ItemBox(Color.Red)
            ItemBox(Color.Green)
            ItemBox(Color.Blue)
            ItemBox(Color.Red)
            ItemBox(Color.Green)
            ItemBox(Color.Blue)
        }
    }
}

@Composable
fun ItemBox(color: Color) {
    Box(
        Modifier
            .size(50.dp, 50.dp)
            .background(color)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldSample() {
    var text by remember {
        mutableStateOf("")
    }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = {
            Text(text = "Name")
        },
        placeholder = {
            Text(text = "Enter your name")
        },
        leadingIcon = {
            Icon(Icons.Default.AccountBox, contentDescription = "")
        },
        trailingIcon = {
            IconButton(onClick = {
                text = ""
            }) {
                Icon(Icons.Default.Clear, contentDescription = "")
            }
        },
        isError = text.length < 5,
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledTextColor = Color.Red,
            focusedLabelColor = Color.Red,
            unfocusedLabelColor = Color.Red,
            disabledLabelColor = Color.Red,
            errorLabelColor = Color.Red,
            cursorColor = Color.Red,
        ),
        shape = RoundedCornerShape(10.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done, // Done, Search, Go, Send, Next, Previous
            keyboardType = KeyboardType.Text, // Text, Number, Email, Password, Phone, Uri
            capitalization = KeyboardCapitalization.Words, // None, Characters, Words, Sentences
        ),
        keyboardActions = KeyboardActions( // Done, Search, Go, Send, Next, Previous
            onDone = {},
            onGo = {},
            onSearch = {},
            onSend = {},
            onNext = {},
            onPrevious = {}
        )
    )
}

@Composable
fun OutlineTextFieldSample() {
    var email by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = email,
        onValueChange = {
            email = it
        },
        label = {
            Text(text = "Email")
        },
        placeholder = {
            Text(text = "Enter your email")
        },
        leadingIcon = {
            Icon(Icons.Default.Email, contentDescription = "")
        },
    )

    Space()

    var password by remember {
        mutableStateOf("")
    }
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
        },
        label = {
            Text(text = "Password")
        },
        placeholder = {
            Text(text = "Enter your password")
        },
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Icon(
                    if (passwordVisibility) Icons.Default.Done else Icons.Default.Lock,
                    contentDescription = ""
                )
            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done, // Done, Search, Go, Send, Next, Previous
            keyboardType = KeyboardType.Password, // Text, Number, Email, Password, Phone, Uri
            capitalization = KeyboardCapitalization.None, // None, Characters, Words, Sentences
        ),
    )
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
        Checkbox(
            checked = isSelected, onCheckedChange = null,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color.Black
            )
        )
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