package com.devrachit.chocochipreader.ui.authScreens.loginScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.devrachit.chocochipreader.ui.theme.primaryColor

@Composable
fun loginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var loginOnClick: () -> Unit = {
        if (username == "admin" && password == "admin") {
            navController.navigate("home")
        }
    }

    backBox()
    photo()
    Column(
        modifier = Modifier
            .padding(top = 50.dp)
            .zIndex(1f)

    ) {
        Heading(title = "Workshop")
        Heading(title = "Attendance")
    }

    Column(
        modifier = Modifier
            .padding(top = 270.dp)
            .zIndex(1f),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Enter your Username",
            color = Color.White,
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 30.dp, top = 10.dp, bottom = 10.dp)
        )
        TextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
        Text(
            text = "Enter your Password",
            color = Color.White,
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 30.dp, top = 30.dp, bottom = 10.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
    }
    Column(
        modifier = Modifier
            .fillMaxHeight(0.78f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {


        Button(
            onClick = { loginOnClick() },
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 30.dp)
                .width(230.dp)
                .clip(RoundedCornerShape(10.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = primaryColor,

                )
            ,
            elevation = ButtonDefaults.buttonElevation(3.dp, 10.dp, 3.dp)
        ) {
            Text(text = "Login")
        }
    }
}