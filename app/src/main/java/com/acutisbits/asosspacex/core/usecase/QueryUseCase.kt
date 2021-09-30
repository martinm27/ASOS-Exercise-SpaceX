package com.acutisbits.asosspacex.core.usecase

import kotlinx.coroutines.flow.Flow

interface QueryUseCase<Result> {

    operator fun invoke(): Flow<Result>
}
