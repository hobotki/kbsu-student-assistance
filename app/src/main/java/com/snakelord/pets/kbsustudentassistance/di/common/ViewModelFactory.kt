package com.snakelord.pets.kbsustudentassistance.di.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.snakelord.pets.kbsustudentassistance.presentation.application.KbsuStudentAssistanceApp
import com.snakelord.pets.kbsustudentassistance.di.login.component.DaggerLoginComponent
import com.snakelord.pets.kbsustudentassistance.domain.mapper.error.StudentErrorMapper
import com.snakelord.pets.kbsustudentassistance.presentation.login.LoginViewModel
import java.lang.IllegalStateException
import javax.inject.Inject

class ViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> createLoginViewModel()
            else -> throw IllegalStateException()
        }
    }

    private fun <T> createLoginViewModel(): T {
        val applicationComponent = KbsuStudentAssistanceApp.applicationComponent
        val loginComponent = DaggerLoginComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
        val loginViewModel = LoginViewModel(
            loginComponent.loginInteractor(),
            applicationComponent.schedulersProvider(),
            StudentErrorMapper()
        )
        return loginViewModel as T
    }
}