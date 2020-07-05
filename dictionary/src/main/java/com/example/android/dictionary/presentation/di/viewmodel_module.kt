package com.example.android.dictionary.presentation.di

import com.example.android.dictionary.presentation.ui.dictionary.DictionaryViewModel
import com.example.android.dictionary.presentation.ui.dictionary.details.DictionaryDetailsViewModel
import org.koin.dsl.module

val dictionaryViewModelModule = module {
    factory { DictionaryViewModel(getDictionaryBySearchUseCase = get()) }
    factory { DictionaryDetailsViewModel(getDictionaryInfoUseCase = get()) }
}