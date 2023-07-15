package com.example.lerword_mvvm.App.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lerword_mvvm.App.Model.Database.UserRepository
import com.example.lerword_mvvm.App.Model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
 * Created by Skyain1 on 13.07.2023.
 */

class LoginViewModel(val repository: UserRepository) : ViewModel() {

    val result = MutableLiveData<Boolean>()

    fun onButtonClicked(user: User){
        GlobalScope.launch(Dispatchers.Main) {
            var isHave = false
            withContext(Dispatchers.IO) {
                isHave = repository.checkField(user)
            }
            result.value = isHave
        }
    }
}