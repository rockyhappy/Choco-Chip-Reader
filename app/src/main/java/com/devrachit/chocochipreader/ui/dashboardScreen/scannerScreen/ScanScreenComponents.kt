package com.devrachit.chocochipreader.ui.dashboardScreen.scannerScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.devrachit.chocochipreader.Constants.customFontFamily
import com.devrachit.chocochipreader.Models.DetailsResponse
import com.devrachit.chocochipreader.ui.theme.grayShade1


@Composable
fun CircularIconButton(
    icon: Int = 0,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .then(modifier)
    )
    {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(grayShade1, CircleShape)
                .clickable {
                    onClick.invoke()
                }
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "Round Image",
                contentScale = ContentScale.None,
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .clip(CircleShape)
            )
        }
    }

}

@Composable
fun head(data: LiveData<DetailsResponse>, onClick: () -> Unit, enabled: Boolean) {
    Column(
        modifier = Modifier
            .padding(10.dp)
    ) {
        Row()
        {
            Text(
                text = "Name: ",
                fontSize = 20.sp,
                fontFamily = customFontFamily,
                fontWeight = Bold,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            )
            Text(
                text = data.value?.name.toString(),
                fontSize = 20.sp,
                fontFamily = customFontFamily,
//                fontWeight = Bold,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            )
        }
        Row()
        {
            Text(
                text = "Student No : ",
                fontSize = 20.sp,
                fontFamily = customFontFamily,
                fontWeight = Bold,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            )
            Text(
                text = data.value?.student_number.toString(),
                fontSize = 20.sp,
                fontFamily = customFontFamily,
//                fontWeight = Bold,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            )
        }
        Row()
        {
            Text(
                text = "Branch: ",
                fontSize = 20.sp,
                fontFamily = customFontFamily,
                fontWeight = Bold,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            )
            Text(
                text = data.value?.branch.toString(),
                fontSize = 20.sp,
                fontFamily = customFontFamily,
//                fontWeight = Bold,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            )
        }
        Row()
        {
            Text(
                text = "Hosteler: ",
                fontSize = 20.sp,
                fontFamily = customFontFamily,
                fontWeight = Bold,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            )
            Text(
                text = data.value?.is_hosteler.toString(),
                fontSize = 20.sp,
                fontFamily = customFontFamily,
//                fontWeight = Bold,
                fontStyle = FontStyle.Normal,
                color = Color.Black
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        {
            Button(
                onClick = {
                onClick()
                },
                enabled = enabled,
            ) {
                Text(
                    text = "Mark Present",
                    fontFamily = customFontFamily,
                    fontSize = 20.sp,
//                fontWeight = Bold,
                    fontStyle = FontStyle.Normal,
                    color = Color.Black
                )
            }
        }

    }
}

