package com.devrachit.chocochipreader

import android.graphics.Bitmap
import android.graphics.ImageFormat
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.hilt.navigation.compose.hiltViewModel
import com.devrachit.chocochipreader.Models.SharedViewModel
import com.devrachit.chocochipreader.ui.dashboardScreen.scannerScreen.ScanScreenViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.ChecksumException
import com.google.zxing.DecodeHintType
import com.google.zxing.FormatException
import com.google.zxing.MultiFormatReader
import com.google.zxing.NotFoundException
import com.google.zxing.PlanarYUVLuminanceSource
import com.google.zxing.common.HybridBinarizer
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

class QrCodeAnalyzer(
    private val onQrCodeScanned: (String) -> Unit,
//    private val viewModel: ScanScreenViewModel
): ImageAnalysis.Analyzer {

    private val supportedImageFormats = listOf(
        ImageFormat.YUV_420_888,
        ImageFormat.YUV_422_888,
        ImageFormat.YUV_444_888
    )
    override fun analyze(image: ImageProxy) {
        if(image.format in supportedImageFormats)
        {
            val bytes = image.planes.first().buffer.toByteArray()

            val rotationDegrees = image.imageInfo.rotationDegrees
            val width = image.height
            val height = image.width



            val source = PlanarYUVLuminanceSource(
                bytes,
                width,
                height,
                0,
                0,
                width,
                height,
                false
            )

            val binaryBmp = BinaryBitmap(HybridBinarizer(source))
            try {
                val result = MultiFormatReader().apply {
                    setHints(
                        mapOf(
                            DecodeHintType.POSSIBLE_FORMATS to arrayListOf(
                                BarcodeFormat.CODE_39
                            )
                        )
                    )
                }.decode(binaryBmp)
//                val viewModel = ScanScreenViewModel()
//                viewModel.onScanRecieved(result.text)
                onQrCodeScanned(result.text)


            } catch (e: Exception) {
                e.printStackTrace()
            }
            finally {
                image.close()
            }

        }
    }

private fun rotateImageBytes(bytes: ByteArray, width: Int, height: Int): ByteArray {
    val rotatedBitmap = Bitmap.createBitmap(height, width, Bitmap.Config.ARGB_8888)
    val rotatedBytes = rotatedBitmap.toByteArray()
    // Rotate the image by 90 degrees clockwise
    for (x in 0 until width) {
        for (y in 0 until height) {
            rotatedBitmap.setPixel(y, width - 1 - x, bytes[x * height + y].toInt())
        }
    }
    return rotatedBytes
}
    private fun Bitmap.toByteArray(): ByteArray {
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }
    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()
        return ByteArray(remaining()).also { get(it) }
    }
    private fun createBitmapFromBytes(bytes: ByteArray, width: Int, height: Int): Bitmap {
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            copyPixelsFromBuffer(ByteBuffer.wrap(bytes))
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degrees: Float): Bitmap {
        val matrix = Matrix().apply { postRotate(degrees) }
        return Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    }
}
