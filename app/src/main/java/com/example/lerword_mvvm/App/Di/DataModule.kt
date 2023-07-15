package com.example.lerword_mvvm.App.Di

import com.example.lerword_mvvm.App.Model.Database.UserDatabase
import com.example.lerword_mvvm.App.Model.Database.UserRepository
import com.example.lerword_mvvm.App.Model.Database.WordsRepository
import org.koin.dsl.module

/*
 * Created by Skyain1 on 13.07.2023.
 */


val DataModule = module {
    single<UserRepository> {
        UserRepository(get())
    }
    single<WordsRepository> {
        WordsRepository()
    }
}