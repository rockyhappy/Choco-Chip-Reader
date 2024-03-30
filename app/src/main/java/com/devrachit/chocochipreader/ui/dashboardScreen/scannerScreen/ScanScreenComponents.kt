package com.devrachit.chocochipreader.ui.dashboardScreen.scannerScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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