package com.devrachit.chocochipreader.ui.authScreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.lifecycleScope
import com.devrachit.chocochipreader.datastore.readFromDataStore
import com.devrachit.chocochipreader.ui.theme.ChocoChipReaderTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.datastore.preferences.core.Preferences
import com.devrachit.chocochipreader.R
import com.devrachit.chocochipreader.ui.dashboardScreen.MainActivity
import com.devrachit.chocochipreader.ui.theme.primaryColor

@ExperimentalMaterial3Api
@AndroidEntryPoint
class SplashScreen : ComponentActivity()  {
    private lateinit var dataStore: DataStore<Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = primaryColor.toArgb()
            ChocoChipReaderTheme {
                dataStore = createDataStore(name = "user")

                LaunchedEffect(key1 = true,  )
                {
                    lifecycleScope.launch {
                        try {
                            val token = readFromDataStore(dataStore, "token").toString()
                            if (token == "null") {
                                val intent = Intent(this@SplashScreen, Auth::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                }
                Column(
                    modifier = Modifier.fillMaxSize().background(primaryColor),
                    horizontalAlignment =Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,

                ){
                    Image(contentDescription =null,
                        painter = painterResource(id = R.drawable.logo2),
                        modifier = Modifier
//                            .height(350.dp)

                            .clip(RoundedCornerShape(20.dp))
                            .zIndex(1f),
//                        contentScale = ContentScale.Crop
                    )
                }

            }
        }
    }
}