package com.devrachit.chocochipreader.ui.dashboardScreen.scannerScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devrachit.chocochipreader.Constants.customFontFamily
import com.devrachit.chocochipreader.Models.DetailsResponse
import com.devrachit.chocochipreader.R
import com.devrachit.chocochipreader.ui.theme.errorColor
import com.devrachit.chocochipreader.ui.theme.grayShade1
import com.devrachit.chocochipreader.ui.theme.primaryColor
import com.devrachit.chocochipreader.ui.theme.secondaryColor
import com.devrachit.chocochipreader.ui.theme.successColor
import androidx.compose.material3.CircularProgressIndicator


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
fun head(
    data: LiveData<DetailsResponse>,
    markpresent: () -> Unit,
    unmarkPresent: () -> Unit,
    enabled: Boolean
) {
    Column(
        modifier = Modifier
            .padding(30.dp)
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
                color = Color.Black,
                modifier=Modifier
                    .padding(start=10.dp)
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
                color = Color.Black,
                modifier=Modifier
                    .padding(start=10.dp)
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
                color = Color.Black,
                modifier=Modifier
                    .padding(start=10.dp)
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
                text =if( data.value?.is_hosteler.toString().toBoolean()) "Yes" else "No",
                fontSize = 20.sp,
                fontFamily = customFontFamily,
//                fontWeight = Bold,
                fontStyle = FontStyle.Normal,
                color = Color.Black,
                modifier=Modifier
                    .padding(start=10.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        {
            Button(
                onClick = {
                    if(data.value?.is_present.toString().toBoolean())
                        unmarkPresent()
                    else
                        markpresent()},
                enabled = enabled,
                modifier = Modifier
                    .width(100.dp)
                    .height(60.dp)
                    .weight(0.4f),
                shape = RoundedCornerShape(16.dp,16.dp,16.dp,16.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor =
                    if(data.value?.is_present.toString().toBoolean()) errorColor
                    else primaryColor
                )
            ) {
                Text(
                    text =
                    if(data.value?.is_present.toString().toBoolean())"Mark Absent"
                    else "Mark Present",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontFamily = customFontFamily,
                )
            }
        }

    }
}

@Composable
fun numberPad(onClick: (String) -> Unit  ){

    var value by remember { mutableStateOf("") }
    Column(
        modifier= Modifier
            .background(Color.White)
            .padding(start = 30.dp, end = 30.dp)
    )
    {
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

        )
        {
            OutlinedTextField(
                value = value,
                onValueChange = { value = it },
                shape = RoundedCornerShape(16.dp,0.dp,0.dp,16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    cursorColor = primaryColor,
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = primaryColor
                ),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                readOnly = true,
                placeholder = {
                    Text(text = "Enter Student Number")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(60.dp)
            )
            Button(
                onClick = {onClick(value)},
                modifier = Modifier
                    .width(100.dp)
                    .height(60.dp)
                    .weight(0.4f),
                shape = RoundedCornerShape(0.dp,16.dp,16.dp,0.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = primaryColor
                )
            ) {
                Text(text = "OK", color = Color.White)
            }
        }
        Row(
            modifier=Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            OutlinedButton(
                onClick = {value+="1"},
                border = BorderStroke(0.5.dp, secondaryColor),
                shape = RoundedCornerShape(15),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(text = "1")
            }
            OutlinedButton(
                onClick = {value+="2"},
                border = BorderStroke(0.5.dp, secondaryColor),
                shape = RoundedCornerShape(15),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(text = "2")
            }
            OutlinedButton(
                onClick = {value+="3"},
                border = BorderStroke(0.5.dp, secondaryColor),
                shape = RoundedCornerShape(15),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(text = "3")
            }
        }
        Row(
            modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            OutlinedButton(
                onClick = {value+="4"},
                border = BorderStroke(0.5.dp, secondaryColor),
                shape = RoundedCornerShape(15),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(text = "4")
            }
            OutlinedButton(
                onClick = {value+="5"},
                border = BorderStroke(0.5.dp, secondaryColor),
                shape = RoundedCornerShape(15),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(text = "5")
            }
            OutlinedButton(
                onClick = {value+="6"},
                border = BorderStroke(0.5.dp, secondaryColor),
                shape = RoundedCornerShape(15),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(text = "6")
            }
        }
        Row(
            modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            OutlinedButton(
                onClick = {value+="7"},
                border = BorderStroke(0.5.dp, secondaryColor),
                shape = RoundedCornerShape(15),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(text = "7")
            }
            OutlinedButton(
                onClick = {value+="8"},
                border = BorderStroke(0.5.dp, secondaryColor),
                shape = RoundedCornerShape(15),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(text = "8")
            }
            OutlinedButton(
                onClick = {value+="9"},
                border = BorderStroke(0.5.dp, secondaryColor),
                shape = RoundedCornerShape(15),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(text = "9")
            }
        }
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            OutlinedButton(
                onClick = {value+="0"},
                border = BorderStroke(0.5.dp, secondaryColor),
                shape = RoundedCornerShape(15),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Text(text = "0")
            }
            OutlinedButton(
                onClick = {value = value.dropLast(1)},
                border = BorderStroke(0.5.dp, secondaryColor),
                shape = RoundedCornerShape(15),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
            ) {
                Icon(contentDescription = null,
                    painter = painterResource(id = R.drawable.back),
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }

}
@Composable
fun showSuccessSnackBar(onClick: () -> Unit)
{
    Snackbar(
        modifier = Modifier.padding(16.dp),
        action = {
            TextButton(onClick = { onClick() }) {
                Text(text = "Dismiss")
            }
        },
        containerColor = successColor
    ) {
        Text(text = "Attendance Marked")
    }
}

@Composable
fun LoadingDialog(isShowingDialog: Boolean, dismissOnBackPress: Boolean = false, dismissOnClickOutside: Boolean = false) {
    if (isShowingDialog) {
        Dialog(
            onDismissRequest = { },
            DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside
            )
        ) {
            DialogContent()
        }
    }
}

@Composable
fun DialogContent() {
    Box(
        modifier = Modifier
            .size(76.dp)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center)
                ,
            color = primaryColor
        )
    }
}