package com.devrachit.chocochipreader.ui.dashboardScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.devrachit.chocochipreader.navigation.appNavHost
import com.devrachit.chocochipreader.ui.dashboardScreen.ListScreen.ListScreen
import com.devrachit.chocochipreader.ui.theme.ChocoChipReaderTheme
import com.devrachit.chocochipreader.ui.theme.primaryColor
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = primaryColor.toArgb()
            ChocoChipReaderTheme {
                val navController: NavHostController = rememberNavController()
                ListScreen(navController =navController)

            }
        }
    }
}
