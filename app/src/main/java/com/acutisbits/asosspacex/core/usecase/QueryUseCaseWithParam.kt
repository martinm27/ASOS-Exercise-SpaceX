package com.acutisbits.asosspacex.core.usecase

import kotlinx.coroutines.flow.Flow

interface QueryUseCaseWithParam<Param, Result> {

    operator fun invoke(param: Param): Flow<Result>
}
