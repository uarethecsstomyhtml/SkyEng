package com.example.android.dictionary.presentation.di

import com.example.android.dictionary.data.repository.DefaultDictionaryRepository
import com.example.android.dictionary.domain.repository.DictionaryRepository
import org.koin.dsl.module

val dictionaryRepositoryModule = module {
    single<DictionaryRepository> { DefaultDictionaryRepository(dictionaryApi = get()) }
}