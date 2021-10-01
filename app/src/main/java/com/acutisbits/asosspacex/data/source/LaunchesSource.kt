package com.acutisbits.asosspacex.data.source

import com.acutisbits.asosspacex.data.model.domain.Launch
import com.acutisbits.asosspacex.util.SortingOrder
import kotlinx.coroutines.flow.Flow

interface LaunchesSource {

    fun getAllLaunches(): Flow<List<Launch>>

    fun getLaunchesSorted(comparator: Comparator<Launch>, sortingOrder: SortingOrder): Flow<List<Launch>>
}
