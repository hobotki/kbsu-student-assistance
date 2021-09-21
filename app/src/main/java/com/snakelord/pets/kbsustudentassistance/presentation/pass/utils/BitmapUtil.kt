package com.snakelord.pets.kbsustudentassistance.presentation.pass.utils

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import java.util.EnumMap

/**
 * Утилитный класс для генерации QR-кода
 *
 * @author Murad Luguev on 11-09-2021
 */
object BitmapUtil {
    private const val UTF8_CHAR_SET = "UTF-8"
    private const val QR_CODE_MARGINS = 1

    /**
     * Функция для генерации QR-кода студента
     *
     * @param studentData информация о студенте в формате JSON
     * @param size размер ImageView, в которую будет вложен QR-код
     *
     * @return QR-код студента типа [Bitmap]
     */
    fun generateQrCodeBitmap(studentData: String, size: Int): Bitmap {
        val qrCodeWriter = QRCodeWriter()
        val qcCodeBitMatrix = qrCodeWriter.encode(
            studentData,
            BarcodeFormat.QR_CODE,
            size,
            size,
            getHintMap()
        )
        val height = qcCodeBitMatrix.height
        val width = qcCodeBitMatrix.width
        return generateBitmap(qcCodeBitMatrix, height, width)
    }

    private fun getHintMap(): Map<EncodeHintType, Any> {
        val hintMap = EnumMap<EncodeHintType, Any>(EncodeHintType::class.java)
        hintMap[EncodeHintType.CHARACTER_SET] = UTF8_CHAR_SET
        hintMap[EncodeHintType.MARGIN] = QR_CODE_MARGINS
        return hintMap
    }

    private fun generateBitmap(bitMatrix: BitMatrix, height: Int, width: Int): Bitmap {
        val pixelsBitmap = Bitmap.createBitmap(
            width,
            height,
            Bitmap.Config.ARGB_8888
        )
        for (x in 0 until width) {
            for (y in 0 until height) {
                pixelsBitmap.setPixel(
                    x, y,
                    getColor(bitMatrix.get(x, y))
                )
            }
        }
        return pixelsBitmap
    }

    private fun getColor(isBlack: Boolean): Int {
        return if (isBlack) {
            Color.WHITE
        } else {
            Color.TRANSPARENT
        }
    }
}