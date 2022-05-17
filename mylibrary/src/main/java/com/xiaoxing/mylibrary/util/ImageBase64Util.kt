package com.kingnew.foreign.server.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import java.io.FileOutputStream
import java.io.OutputStream


/**
 * Created by ch on 2021/01/05.
 *
 *  图片工具类
 */
object ImageBase64Util {


    /**
     * base64转为bitmap
     * @param base64Data
     * @return
     */
    fun base64ToBitmap(base64Data: String): Bitmap? {
        val byte = Base64.decode(base64Data.split(",")[1], Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(byte, 0, byte.size)
        return bitmap
    }

    /**
     * Save Bitmap
     * @param bm   picture to save
     */
    fun saveBitmap(path: String, bm: Bitmap): Boolean {
        return try {
            val os: OutputStream = FileOutputStream(path)
            bm.compress(Bitmap.CompressFormat.JPEG, 100, os)
            os.flush()
            os.close()
            true
        } catch (e: Exception) {
            Log.e("TAG", "", e)
            false
        }
    }


}