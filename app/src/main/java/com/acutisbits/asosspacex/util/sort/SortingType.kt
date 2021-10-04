package com.acutisbits.asosspacex.util.sort

import com.acutisbits.asosspacex.data.model.domain.Launch
import com.acutisbits.asosspacex.util.sort.LaunchComparator

enum class SortingType {
    YEAR,
    SUCCESSION
}

object YearComparator : LaunchComparator() {

    override fun checkProperty(p0: Launch, p1: Launch): Int =
        p0.launchYear.compareTo(p1.launchYear)

}

object SuccessionComparator : LaunchComparator() {

    override fun checkProperty(p0: Launch, p1: Launch): Int =
        p0.isLaunchSuccessful.compareTo(p1.isLaunchSuccessful)
}


