package com.devrachit.chocochipreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devrachit.chocochipreader.navigation.AuthScreens
import com.devrachit.chocochipreader.navigation.authNavHost
import com.devrachit.chocochipreader.ui.authScreens.loginScreen.loginScreen
import com.devrachit.chocochipreader.ui.theme.ChocoChipReaderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Auth : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChocoChipReaderTheme {
                val navController :NavHostController= rememberNavController()
                authNavHost(navHostController =navController)
            }
        }
    }
}