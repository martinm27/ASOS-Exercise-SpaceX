package com.acutisbits.asosspacex.di

import com.acutisbits.asosspacex.data.usecase.QueryAllLaunches
import org.koin.core.module.Module
import org.koin.dsl.module

fun dataModule(): Module = module {

    single { QueryAllLaunches(get()) }
}
