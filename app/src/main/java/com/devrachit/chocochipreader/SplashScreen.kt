package com.devrachit.chocochipreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.devrachit.chocochipreader.ui.theme.ChocoChipReaderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : ComponentActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChocoChipReaderTheme {

            }
        }
    }
}