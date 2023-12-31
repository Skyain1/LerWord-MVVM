package com.example.lerword_mvvm.App.Model.Database

import com.example.lerword_mvvm.App.Model.User


/*
 * Created by Skyain1 on 24.06.2023.
 */

class UserRepository (private val userDao: UserDao) {

    suspend fun insert(user: User){
        userDao.insert(user)
    }
    suspend fun checkField(user: User) : Boolean{
        return userDao.checkUserExists(user.email, user.password)>0
    }
}