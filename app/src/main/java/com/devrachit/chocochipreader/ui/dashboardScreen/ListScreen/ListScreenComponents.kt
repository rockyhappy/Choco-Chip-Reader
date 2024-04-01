package com.devrachit.chocochipreader.ui.dashboardScreen.ListScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devrachit.chocochipreader.R
import com.devrachit.chocochipreader.ui.theme.grayShade4
import com.devrachit.chocochipreader.ui.theme.primaryColor


@ExperimentalMaterial3Api
@Composable
fun ProfileNavigation(
    selected: Int,
    onItemSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        ProfileNavigationItem(
            text = "Day 1",
            selected = selected == 1,
            onClick = { onItemSelected(1) }
        )

        ProfileNavigationItem(
            text = "Day 2",
            selected = selected == 2,
            onClick = { onItemSelected(2) }
        )

        ProfileNavigationItem(
            text = "Day 3",
            selected = selected == 3,
            onClick = { onItemSelected(3) }
        )

        ProfileNavigationItem(
            text = "Day 4",
            selected = selected == 4,
            onClick = { onItemSelected(4) }
        )
    }
}

@Composable
private fun ProfileNavigationItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = if (selected) primaryColor else Color.Black,
            modifier = Modifier.clickable { onClick() }
        )
        if (selected) {
            Image(
                painter = painterResource(id = R.drawable.dot_primary_color),
                contentDescription = null,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
@ExperimentalMaterial3Api
@Preview
@Composable
fun ProfileNavigationPreview() {
    ProfileNavigation(selected = 1, onItemSelected = {})
}