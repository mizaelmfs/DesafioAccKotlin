package com.example.projeto.desafioaccandroidkotlin.Utils

import android.widget.ImageView
import android.graphics.BitmapFactory
import android.util.Base64;
import org.jetbrains.anko.imageBitmap


abstract class BindingAdapter {

    companion object {

        @android.databinding.BindingAdapter("app:ImgBaseCustom")
        @JvmStatic
        fun setImgBaseCustom(image: ImageView, base: String?) {
            if (base != null) {
                val base64Image = base.split(",")[1]
                val decodedString = Base64.decode(base64Image, Base64.DEFAULT)
                val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                image.imageBitmap = decodedByte
            }
        }
    }
}