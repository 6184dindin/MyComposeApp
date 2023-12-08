package com.example.mycomposeapp.screen

sealed class Screen(val route: String) {
    object A : Screen("screen_a")
    object B : Screen("screen_b")
    object C : Screen("screen_c/{userName}/{age}") {
        fun createRoute(userName: String) = "screen_c/$userName"
    }
}
