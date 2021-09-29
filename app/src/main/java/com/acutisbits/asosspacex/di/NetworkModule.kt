package com.acutisbits.asosspacex.di

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

private val json = Json {
    ignoreUnknownKeys = true
}

@ExperimentalSerializationApi
fun networkModule() = module {

    single<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder().apply {
            connectTimeout(100, TimeUnit.SECONDS)
            readTimeout(100, TimeUnit.SECONDS)
            addInterceptor(get<Interceptor>())
        }
            .build()
    }
}
