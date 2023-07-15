package com.example.lerword_mvvm.App.View.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.lerword_mvvm.App.Model.User
import com.example.lerword_mvvm.App.ViewModel.LoginViewModel
import com.example.lerword_mvvm.App.ViewModel.MainViewModel
import com.example.lerword_mvvm.R
import com.example.lerword_mvvm.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/*
 * Created by Skyain1 on 13.07.2023.
 */


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val vm by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enter.setOnClickListener {
            val user = User(
                email = binding.email.text.toString(),
                password = binding.password.text.toString()
            )
            vm.onButtonClicked(user)
        }
        binding.toSignUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }

        vm.result.observe(this) {
            if (it) {
                successfulAuthorization()
            } else {
                showErrorToast("Ошибка входа")
            }
        }
    }

    private fun successfulAuthorization() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showErrorToast(error: String) {
        Toast.makeText(this@LoginActivity, error, Toast.LENGTH_SHORT).show()
    }
}