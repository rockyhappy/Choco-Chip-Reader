package com.devrachit.chocochipreader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devrachit.chocochipreader.ui.dashboardScreen.ListScreen.ListScreen
import com.devrachit.chocochipreader.ui.dashboardScreen.scannerScreen.scanScreen

@Composable
fun appNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = AppScreens.ScannerScreen.route
    )
    {
        composable(AppScreens.ScannerScreen.route){
            scanScreen(navController = navHostController)
        }
        composable(AppScreens.ListScreen.route){
            ListScreen(navController = navHostController)
        }
    }
}