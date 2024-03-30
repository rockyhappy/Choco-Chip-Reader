package com.devrachit.chocochipreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraSelector
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import android.Manifest
import android.content.pm.PackageManager
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.Camera
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.compose.ui.viewinterop.AndroidView
import com.devrachit.chocochipreader.ui.theme.ChocoChipReaderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChocoChipReaderTheme {

                val lifecycleOwner = LocalLifecycleOwner.current
                var code by remember { mutableStateOf("") }
                var context = LocalContext.current
                val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
                var hasCameraPermission by remember {
                    mutableStateOf(
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    )
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

                        AndroidView(factory = { context ->
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
                                    STRATEGY_KEEP_ONLY_LATEST
                                )
                                .build()
                            imageAnalysis.setAnalyzer(
                                ContextCompat.getMainExecutor(context),
                                QrCodeAnalyzer {

                                    code = it
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
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Scanned QR Code: $code",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                }
            }
        }
    }
}
