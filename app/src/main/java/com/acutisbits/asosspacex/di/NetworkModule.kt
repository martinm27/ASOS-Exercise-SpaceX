package com.acutisbits.asosspacex.di

import com.acutisbits.asosspacex.BuildConfig
import com.acutisbits.asosspacex.data.network.ASOSSpaceXService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
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

    single<ASOSSpaceXService> {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(get())
            .build()
            .create(ASOSSpaceXService::class.java)
    }
}
