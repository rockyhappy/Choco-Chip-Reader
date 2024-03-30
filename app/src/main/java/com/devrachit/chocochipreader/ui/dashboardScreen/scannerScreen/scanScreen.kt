package com.devrachit.chocochipreader.ui.dashboardScreen.scannerScreen


import android.Manifest
import android.content.pm.PackageManager
import android.util.Size
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.devrachit.chocochipreader.QrCodeAnalyzer
import com.devrachit.chocochipreader.R

@ExperimentalMaterial3Api
@Composable
fun scanScreen(navController: NavController) {

    val viewModel = hiltViewModel<ScanScreenViewModel>()
    val lifecycleOwner = LocalLifecycleOwner.current
    var code by remember { mutableStateOf("") }
    var context = LocalContext.current
    var isFlashOn by remember { mutableStateOf(false) }
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val options = listOf("day 1", "day 2", "day 3", "day 4")
    var selectedIndex by remember { mutableStateOf(0) }
    var expanded by remember { mutableStateOf(false) }
    var markAttendanceEnabledButton by remember { mutableStateOf(true) }



    val markPresent :() -> Unit ={
        viewModel.markPresent(options[selectedIndex])
    }

    val onFlashClick: () -> Unit = {
        isFlashOn = !isFlashOn
//        markPresent()
    }

    val cameraProvider = cameraProviderFuture.get()
    cameraProvider?.let { provider ->
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        val camera = provider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector
        )
        camera?.cameraControl?.enableTorch(isFlashOn)
    }
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }



    /**
     * These are the variables to manage the scan state
     */
    val loading = viewModel.loading.collectAsStateWithLifecycle()

    if (loading.value) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(15.dp),
            contentAlignment = Alignment.Center
        ) {
        }
        markAttendanceEnabledButton = false
    }
    if(!loading.value)
    {
        markAttendanceEnabledButton = true
    }



    /**
     * These will manage if the scan is success ful and the data is achieved then what to do
     */
    var scanSuccess = viewModel.scanSuccess.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    if (scanSuccess.value) {
        showBottomSheet = true
        viewModel._scanSuccess.value = false
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
            modifier= Modifier
                .wrapContentHeight(),
            containerColor = Color.White
        ) {
            val data = viewModel.sharedViewModel.data
            head(data = data, markPresent , markAttendanceEnabledButton)

        }
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasCameraPermission = isGranted
        }
    )
    LaunchedEffect(key1 = true)
    {
        launcher.launch(Manifest.permission.CAMERA)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (hasCameraPermission) {
            Box()
            {
                AndroidView(
                    factory = { context ->
                        val previewView = PreviewView(context)
                        val preview = Preview.Builder().build()
                        val selector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()
                        preview.setSurfaceProvider(previewView.surfaceProvider)
                        val imageAnalysis = ImageAnalysis.Builder()
                            .setTargetResolution(
                                Size(
                                    previewView.width,
                                    previewView.height
                                )
                            )
                            .setBackpressureStrategy(
                                ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
                            )
                            .build()
                        imageAnalysis.setAnalyzer(
                            ContextCompat.getMainExecutor(context),
                            QrCodeAnalyzer { scannedCode ->
                                if (code != scannedCode) {
                                    code = scannedCode
                                    viewModel.onScanRecieved(scannedCode)
                                }
                            }
                        )
                        try {
                            cameraProviderFuture.get().bindToLifecycle(
                                lifecycleOwner,
                                selector,
                                preview,
                                imageAnalysis
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        previewView
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize()
                )
                CircularIconButton(
                    icon = R.drawable.flash,
                    onClick = {
                        onFlashClick()
                    },
                    modifier = Modifier.padding(30.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp),
                    contentAlignment = Alignment.TopCenter
                )
                {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        modifier = Modifier
                            .width(100.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                    ) {
                        TextField(
                            value = options[selectedIndex],
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.menuAnchor(),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            ),
//                            textAlign = TextAlign.Center,
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            options.forEachIndexed { index, item ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = item,
                                            fontWeight = if (index == selectedIndex) FontWeight.Bold else null
                                        )
                                    },
                                    onClick = {
                                        selectedIndex = index
                                        expanded = false
                                        Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                    }
                                )
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 30.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    ExtendedFloatingActionButton(
                        icon = { Icon(Icons.Filled.Person, contentDescription = null) },
                        text = { Text("Enter code Manually") },
                        onClick = { /* do something */ },
                        containerColor = Color.White,
                    )
                }
            }
        }
    }

}
