package com.acutisbits.asosspacex.util.sort

import com.acutisbits.asosspacex.data.model.domain.Launch

abstract class LaunchComparator : Comparator<Launch> {

    override fun compare(p0: Launch?, p1: Launch?): Int =
        when {
            p0 == null -> -1
            p1 == null -> 1
            else -> checkProperty(p0, p1)
        }

    abstract fun checkProperty(p0: Launch, p1: Launch): Int
}
