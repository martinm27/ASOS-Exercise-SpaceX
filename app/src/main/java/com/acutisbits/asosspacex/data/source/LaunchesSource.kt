package com.acutisbits.asosspacex.data.source

import com.acutisbits.asosspacex.data.model.domain.Launch
import com.acutisbits.asosspacex.util.SortingOrder
import com.acutisbits.asosspacex.util.SortingType
import kotlinx.coroutines.flow.Flow

interface LaunchesSource {

    fun getAllLaunches(): Flow<List<Launch>>

    suspend fun sortLaunches(sortingType: SortingType, sortingOrder: SortingOrder)
}
