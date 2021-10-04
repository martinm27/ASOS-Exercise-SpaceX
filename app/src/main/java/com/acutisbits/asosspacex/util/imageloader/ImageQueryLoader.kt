package com.acutisbits.asosspacex.util.imageloader

import android.widget.ImageView

interface ImageQueryLoader {

    fun loadWithKey(
        target: ImageView,
        key: String,
        errorResourceId: Int
    )
}
