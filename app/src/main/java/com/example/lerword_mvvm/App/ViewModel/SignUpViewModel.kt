package com.example.lerword_mvvm.App.ViewModel

import android.app.Application
import android.util.Patterns
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

class SignUpViewModel(val repository: UserRepository) : ViewModel() {

    val result = MutableLiveData<Boolean>()

    fun onButtonClicked(email: String, password: String, confirmpas: String) {
        val user = User(email = email, password = password)

        if (validateInput(email, password, confirmpas)) {
            GlobalScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    repository.insert(user)
                }
                result.value = true
            }
        } else {
            result.value = false
        }
    }

    private fun validateInput(email: String, password: String, confirmpas: String): Boolean {
        if (email.isNotEmpty() && password.isNotEmpty() && confirmpas.isNotEmpty()
        ) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (password.equals(confirmpas)) {
                    return true
                } else {
                    result.value = false
                }
            } else {
                result.value = false
            }
        }
        return false
    }
}