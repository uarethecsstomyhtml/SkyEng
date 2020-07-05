package com.example.android.ui_components

import android.widget.ImageView
import com.bumptech.glide.Glide

private const val IMG_BASE_URL = "https://dictionary.skyeng.ru"

fun ImageView.loadImg(postfixUrl: String) {
    Glide.with(this.context).load(IMG_BASE_URL + postfixUrl).into(this)
}
