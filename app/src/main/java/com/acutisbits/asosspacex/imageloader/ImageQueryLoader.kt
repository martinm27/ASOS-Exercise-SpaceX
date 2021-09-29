package com.acutisbits.asosspacex.imageloader

import android.widget.ImageView

interface ImageQueryLoader {

    fun loadWithKey(
        target: ImageView,
        key: String,
        errorResourceId: Int
    )
}
