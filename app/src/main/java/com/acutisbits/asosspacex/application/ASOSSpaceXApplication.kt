package com.acutisbits.asosspacex.application

import android.app.Application
import com.acutisbits.asosspacex.core.log.LoggerImpl
import com.acutisbits.asosspacex.core.log.Lumber
import com.acutisbits.asosspacex.di.appModule
import com.acutisbits.asosspacex.di.databaseModule
import com.acutisbits.asosspacex.di.navigationModule
import com.acutisbits.asosspacex.di.networkModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ASOSSpaceXApplication : Application() {

    @ExperimentalSerializationApi
    override fun onCreate() {
        super.onCreate()

        initKoin()
        Lumber.initialise(LoggerImpl())
    }

    @ExperimentalSerializationApi
    private fun initKoin() {
        startKoin {
            androidContext(this@ASOSSpaceXApplication)
            modules(
                listOf(
                    appModule(),
                    networkModule(),
                    databaseModule(),
                    navigationModule(),
                )
            )
        }
    }
}
