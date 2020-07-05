package com.example.android.network

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import org.koin.android.BuildConfig.DEBUG
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://dictionary.skyeng.ru"

val networkModule = module {
    single { gson() }
    single { okHttpClient() }
    single { retrofitClient(gson = get(), okHttpClient = get()) }
}

private fun gson(): Gson {
    return GsonBuilder().create()
}


private fun okHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()

    httpLoggingInterceptor.level = if (DEBUG) BODY else NONE

    return with(OkHttpClient.Builder()) {
        addInterceptor(httpLoggingInterceptor)
        build()
    }
}

private fun retrofitClient(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
}
