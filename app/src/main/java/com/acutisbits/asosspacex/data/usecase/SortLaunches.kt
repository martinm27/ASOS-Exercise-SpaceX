package com.acutisbits.asosspacex.data.usecase

import com.acutisbits.asosspacex.core.usecase.CommandUseCaseWithParam
import com.acutisbits.asosspacex.data.source.LaunchesSource
import com.acutisbits.asosspacex.util.sort.SortingOrder
import com.acutisbits.asosspacex.util.sort.SortingType

class SortLaunches(private val source: LaunchesSource) : CommandUseCaseWithParam<SortLaunchesParam> {

    override suspend fun invoke(param: SortLaunchesParam) = source.sortLaunches(param.sortingType, param.sortingOrder)
}

data class SortLaunchesParam(val sortingType: SortingType, val sortingOrder: SortingOrder)
