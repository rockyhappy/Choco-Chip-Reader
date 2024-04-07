package com.devrachit.chocochipreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devrachit.chocochipreader.navigation.AuthScreens
import com.devrachit.chocochipreader.navigation.authNavHost
import com.devrachit.chocochipreader.ui.authScreens.loginScreen.loginScreen
import com.devrachit.chocochipreader.ui.theme.ChocoChipReaderTheme
import com.devrachit.chocochipreader.ui.theme.primaryColor
import dagger.hilt.android.AndroidEntryPoint
@ExperimentalMaterial3Api
@AndroidEntryPoint
class Auth : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = primaryColor.toArgb()
            ChocoChipReaderTheme {
                val navController :NavHostController= rememberNavController()
                authNavHost(navHostController =navController)
            }
        }
    }
}