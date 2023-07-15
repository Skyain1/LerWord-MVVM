package com.example.lerword_mvvm.App

import android.app.Application
import com.example.lerword_mvvm.App.Di.DataModule
import com.example.lerword_mvvm.App.Di.DatabaseModule
import com.example.lerword_mvvm.App.Di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/*
 * Created by Skyain1 on 13.07.2023.
 */


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(ViewModelModule, DataModule, DatabaseModule))
        }
    }
}