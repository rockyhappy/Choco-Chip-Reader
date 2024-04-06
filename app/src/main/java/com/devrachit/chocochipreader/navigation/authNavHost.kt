package com.devrachit.chocochipreader.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devrachit.chocochipreader.ui.authScreens.loginScreen.loginScreen

@ExperimentalMaterial3Api
@Composable
fun authNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = AuthScreens.LoginScreen.route
    ){
        composable(AuthScreens.LoginScreen.route){
            loginScreen(navController = navHostController)
        }
    }
}