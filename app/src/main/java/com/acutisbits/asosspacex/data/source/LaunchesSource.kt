package com.acutisbits.asosspacex.data.source

import com.acutisbits.asosspacex.data.model.domain.Launch
import com.acutisbits.asosspacex.util.sort.SortingOrder
import kotlinx.coroutines.flow.Flow

interface LaunchesSource {

    fun getAllLaunches(): Flow<List<Launch>?>

    suspend fun sortLaunches(year: String, isLaunchSuccessful: Boolean, sortingOrder: SortingOrder)
}
