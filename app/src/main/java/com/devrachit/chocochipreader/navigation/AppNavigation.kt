package com.devrachit.chocochipreader.navigation

sealed class AppScreens(val route: String) {

    object ScannerScreen : AppScreens("ScannerScreen")

    object ListScreen : AppScreens("ListScreen")
}