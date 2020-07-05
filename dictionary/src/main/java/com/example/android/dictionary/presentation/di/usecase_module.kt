package com.example.android.dictionary.presentation.di

import com.example.android.dictionary.domain.usecase.GetDictionaryBySearchUseCase
import com.example.android.dictionary.domain.usecase.GetDictionaryInfoUseCase
import org.koin.dsl.module

val dictionaryUseCaseModule = module {
    factory { GetDictionaryBySearchUseCase(dictionaryRepository = get()) }
    factory { GetDictionaryInfoUseCase(dictionaryRepository = get()) }
}