package com.acutisbits.asosspacex.di

import com.acutisbits.asosspacex.data.source.LaunchesSource
import com.acutisbits.asosspacex.data.source.LaunchesSourceImpl
import com.acutisbits.asosspacex.data.usecase.QueryAllLaunches
import com.acutisbits.asosspacex.data.usecase.QueryCompanyInfo
import org.koin.core.module.Module
import org.koin.dsl.module

fun dataModule(): Module = module {

    single<LaunchesSource> { LaunchesSourceImpl(get()) }

    single { QueryAllLaunches(get()) }
    single { QueryCompanyInfo(get()) }
}
