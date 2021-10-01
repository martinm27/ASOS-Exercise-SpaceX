package com.acutisbits.asosspacex.data.usecase

import com.acutisbits.asosspacex.core.usecase.QueryUseCase
import com.acutisbits.asosspacex.data.model.domain.Launch
import com.acutisbits.asosspacex.data.source.LaunchesSource
import kotlinx.coroutines.flow.Flow

class QueryAllLaunches(private val source: LaunchesSource) : QueryUseCase<List<Launch>> {

    override fun invoke(): Flow<List<Launch>> = source.getAllLaunches()

}
