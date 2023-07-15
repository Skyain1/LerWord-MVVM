package com.example.lerword_mvvm.App.View.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.lerword_mvvm.App.ViewModel.LoginViewModel
import com.example.lerword_mvvm.App.ViewModel.SignUpViewModel
import com.example.lerword_mvvm.databinding.ActivitySignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/*
 * Created by Skyain1 on 13.07.2023.
 */


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val vm by viewModel<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enter.setOnClickListener {
            vm.onButtonClicked(
                binding.email.text.toString(),
                binding.password.text.toString(),
                binding.confirmpass.text.toString()
            )
        }
        binding.toLogin.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
        }
        vm.result.observe(this) {
            if (it) {
                successfulRegistration()
            } else {
                showErrorToast("Ошибка регистрации")
            }
        }
    }

    private fun successfulRegistration() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun showErrorToast(error: String) {
        Toast.makeText(this@SignUpActivity, error, Toast.LENGTH_SHORT).show()
    }
}