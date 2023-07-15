package com.example.lerword_mvvm.App.Di

import com.example.lerword_mvvm.App.Model.Database.UserDao
import com.example.lerword_mvvm.App.Model.Database.UserDatabase
import org.koin.dsl.module

/*
 * Created by Skyain1 on 13.07.2023.
 */

val DatabaseModule = module {

    single<UserDao> {
        UserDatabase.getDatabase(get()).getUserDao()
    }
}