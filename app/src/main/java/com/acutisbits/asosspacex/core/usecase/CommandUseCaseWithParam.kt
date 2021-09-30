package com.acutisbits.asosspacex.core.usecase

interface CommandUseCaseWithParam<Param> {

    suspend operator fun invoke(param: Param)
}
