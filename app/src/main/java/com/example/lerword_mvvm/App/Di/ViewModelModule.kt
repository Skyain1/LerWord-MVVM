package com.example.lerword_mvvm.App.Di

import com.example.lerword_mvvm.App.ViewModel.LoginViewModel
import com.example.lerword_mvvm.App.ViewModel.MainViewModel
import com.example.lerword_mvvm.App.ViewModel.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*
 * Created by Skyain1 on 13.07.2023.
 */


val ViewModelModule = module {

    viewModel<LoginViewModel> {
        LoginViewModel(get())
    }

    viewModel<SignUpViewModel> {
        SignUpViewModel(get())
    }
    viewModel<MainViewModel> {
        MainViewModel(get())
    }

}