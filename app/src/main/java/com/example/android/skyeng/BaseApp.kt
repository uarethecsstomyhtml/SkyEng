package com.example.android.skyeng

import android.app.Application
import com.example.android.dictionary.presentation.di.dictionaryNetworkModule
import com.example.android.dictionary.presentation.di.dictionaryRepositoryModule
import com.example.android.dictionary.presentation.di.dictionaryUseCaseModule
import com.example.android.dictionary.presentation.di.dictionaryViewModelModule
import com.example.android.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber


class BaseApp : Application() {

    private val modules = listOf(networkModule)

    private val dictionaryModules = listOf(
        dictionaryNetworkModule,
        dictionaryRepositoryModule,
        dictionaryUseCaseModule,
        dictionaryViewModelModule
    )


    override fun onCreate() {
        super.onCreate()
        setupLogging()
        setupDependencyInjection()
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun setupDependencyInjection() {
        startKoin {
            printLogger(level = Level.DEBUG)

            androidContext(this@BaseApp)

            modules(modules)
            modules(dictionaryModules)
        }
    }

}