package com.example.mycomposeapp


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycomposeapp.ui.theme.MyComposeAppTheme


enum class BottomBarContent {
    HOME, FAVORITE, PROFILE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldDemo() {
    var currentContent by remember { mutableStateOf(BottomBarContent.HOME) }

    Scaffold(
        topBar = {
            // TopBar (AppBar)
            TopAppBar(
                title = { Text("My App") },
                actions = {
                    // Các action trên TopBar
                    IconButton(onClick = { /* Do something */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { /* Do something */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }
                }
            )
        },
        bottomBar = {
            // BottomBar
            BottomAppBar(
                content = {
                    // Các nút ở BottomBar
                    IconButton(onClick = {
                        currentContent = BottomBarContent.HOME
                    }) {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        currentContent = BottomBarContent.FAVORITE
                    }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                    }
                    IconButton(onClick = {
                        currentContent = BottomBarContent.PROFILE
                    }) {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    }
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(paddingValues = it)) {
                // Nội dung chính của ứng dụng
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Nội dung cho mỗi mục trong BottomAppBar
                    when (currentContent) {
                        BottomBarContent.HOME -> {
                            Text("Home Content", color = Color.Black)
                        }

                        BottomBarContent.FAVORITE -> {
                            Text("Favorite Content", color = Color.Black)
                        }

                        BottomBarContent.PROFILE -> {
                            Text("Profile Content", color = Color.Black)
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScaffoldDemoPreview() {
    MyComposeAppTheme {
        ScaffoldDemo()
    }
}
