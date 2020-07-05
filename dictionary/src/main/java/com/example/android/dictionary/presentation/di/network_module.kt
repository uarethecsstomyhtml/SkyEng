package com.example.android.dictionary.presentation.di

import com.example.android.dictionary.data.remote.DictionaryApi
import org.koin.dsl.module
import retrofit2.Retrofit

private fun dictionaryApi(retrofit: Retrofit) = retrofit.create(DictionaryApi::class.java)

val dictionaryNetworkModule = module {
    single { dictionaryApi(retrofit = get()) }
}