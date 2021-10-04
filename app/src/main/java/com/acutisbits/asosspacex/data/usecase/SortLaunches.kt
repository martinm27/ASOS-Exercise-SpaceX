package com.acutisbits.asosspacex.data.usecase

import com.acutisbits.asosspacex.core.usecase.CommandUseCaseWithParam
import com.acutisbits.asosspacex.data.source.LaunchesSource
import com.acutisbits.asosspacex.util.sort.SortingOrder

class SortLaunches(private val source: LaunchesSource) : CommandUseCaseWithParam<SortLaunchesParam> {

    override suspend fun invoke(param: SortLaunchesParam) = source.sortLaunches(param.year, param.isLaunchSuccessful, param.sortingOrder)
}

data class SortLaunchesParam(val year: String, val isLaunchSuccessful: Boolean, val sortingOrder: SortingOrder)
