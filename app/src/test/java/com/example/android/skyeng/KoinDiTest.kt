package com.example.android.skyeng

import com.example.android.dictionary.presentation.di.dictionaryNetworkModule
import com.example.android.dictionary.presentation.di.dictionaryRepositoryModule
import com.example.android.dictionary.presentation.di.dictionaryUseCaseModule
import com.example.android.dictionary.presentation.di.dictionaryViewModelModule
import com.example.android.network.networkModule
import org.junit.Test
import org.junit.experimental.categories.Category
import org.koin.test.AutoCloseKoinTest
import org.koin.test.category.CheckModuleTest
import org.koin.test.check.checkModules

@Category(CheckModuleTest::class)
class KoinDiTest : AutoCloseKoinTest() {

    @Test
    fun checkModules() = checkModules {
        modules(
            networkModule,
            dictionaryNetworkModule,
            dictionaryRepositoryModule,
            dictionaryUseCaseModule,
            dictionaryViewModelModule
        )
    }
}