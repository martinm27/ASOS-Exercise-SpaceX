package com.acutisbits.asosspacex.coreui.utils

import android.view.View

fun View.show(show: Boolean = true) {
    if (visibility == View.VISIBLE && show) return
    if (visibility == View.GONE && !show) return
    visibility = if (show) View.VISIBLE else View.GONE
}

fun View.hide() = show(false)
