package com.devrachit.chocochipreader.ui.authScreens.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.devrachit.chocochipreader.Constants.customFontFamily
import com.devrachit.chocochipreader.R
import com.devrachit.chocochipreader.ui.theme.primaryColor

@Composable
fun backBox()
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .background(Color.White)
            .zIndex(0f)

    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp))
                .background(primaryColor)
                .zIndex(0f)
        )

    }
}
@Composable
fun photo()
{
    Image(contentDescription =null, painter = painterResource(id = R.drawable.img),
        modifier = Modifier
            .padding(start=30.dp,top=20.dp)
            .height(150.dp)
            .clip(RoundedCornerShape(20.dp))
            .zIndex(1f),
        contentScale = ContentScale.Crop
    )
}
@Composable
fun Heading(title: String)
{
    Text(text = title,
        fontFamily = customFontFamily,
        modifier = Modifier.padding(start=20.dp,top=20.dp),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        )
}
