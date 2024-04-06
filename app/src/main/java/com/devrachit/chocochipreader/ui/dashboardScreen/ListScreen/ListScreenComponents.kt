package com.devrachit.chocochipreader.ui.dashboardScreen.ListScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devrachit.chocochipreader.R
import com.devrachit.chocochipreader.ui.theme.grayShade4
import com.devrachit.chocochipreader.ui.theme.primaryColor
import kotlinx.coroutines.delay


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

@Composable
fun FloatingNavigationView(
    menuItems: List<String>,
    onMenuItemClick: (String) -> Unit
) {
    var isMenuOpen by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenSize = remember { DpSize(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp) }
    val scale = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    val puff = remember { Animatable(1f) }

    val backgroundColors = listOf(
        Color(0xFFF4DBD8),
        Color(0xFFEAC4C1),
        Color(0xFFE0ADAD),
        Color(0xFFD59799),
        Color(0xFFCB8185)
    )

    Scaffold(
        containerColor = Color(0xFFF4DBD8),
        floatingActionButton = {
            FloatingActionButton(containerColor = Color(0xFFC09891), onClick = { isMenuOpen = !isMenuOpen }) {
                Icon(Icons.Default.Menu, contentDescription = "Menu")
            }
        },
        content = {
            AnimatedVisibility(visible = isMenuOpen, modifier = Modifier.padding(it)) {
                BoxView(visible = isMenuOpen, screenSize = screenSize) {
                    Column {
                        menuItems.forEachIndexed { index, item ->
                            val backgroundColor = backgroundColors[index % backgroundColors.size]

                            LaunchedEffect(isMenuOpen) {
                                delay(index * 50L)
                                scale.animateTo(
                                    targetValue = if (isMenuOpen) 1f else 0f,
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioLowBouncy,
                                        stiffness = Spring.StiffnessVeryLow
                                    )
                                )
                                offsetY.animateTo(
                                    targetValue = if (isMenuOpen) 0f else 50f,
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioLowBouncy,
                                        stiffness = Spring.StiffnessVeryLow
                                    )
                                )

                                if (isMenuOpen) {
                                    puff.animateTo(
                                        targetValue = 1.1f,
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioLowBouncy,
                                            stiffness = Spring.StiffnessLow
                                        )
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .graphicsLayer(
                                        scaleX = scale.value * puff.value,
                                        scaleY = scale.value * puff.value
                                    )
                                    .offset(y = offsetY.value.dp)
                                    .fillMaxWidth()
                                    .background(backgroundColor)
                            ) {
                                Text(
                                    text = item,
                                    modifier = Modifier
                                        .clickable { onMenuItemClick(item) }
                                        .padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun BoxView(
    visible: Boolean,
    screenSize: DpSize,
    content: @Composable () -> Unit
) {
    val transition = updateTransition(targetState = visible, label = "CircularRevealTransition")
    val size by transition.animateDp(
        label = "Size",
        transitionSpec = {
            if (targetState) {
                spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow)
            } else {
                tween(durationMillis = 600, easing = FastOutSlowInEasing)
            }
        }
    ) { state ->
        if (state) maxOf(screenSize.width, screenSize.height) * 1.5f else 0.dp
    }

    val alpha by transition.animateFloat(
        label = "Alpha",
        transitionSpec = {
            tween(durationMillis = 600, easing = FastOutSlowInEasing)
        }
    ) { state ->
        if (state) 1f else 0f
    }

    Box(
        modifier = Modifier
            .size(size)
            .clip(RectangleShape)
            .background(Color(0xFF775144).copy(alpha = alpha))
    ) {
        content()
    }
}