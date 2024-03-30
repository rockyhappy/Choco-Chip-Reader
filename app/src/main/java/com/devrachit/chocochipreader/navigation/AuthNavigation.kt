package com.devrachit.chocochipreader.navigation

sealed class AuthScreens(val route: String) {
    object LoginScreen : AuthScreens("LoginScreen")
}