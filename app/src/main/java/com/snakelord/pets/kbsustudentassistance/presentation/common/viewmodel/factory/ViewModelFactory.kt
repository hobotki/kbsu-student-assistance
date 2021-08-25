package com.snakelord.pets.kbsustudentassistance.presentation.common.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.snakelord.pets.kbsustudentassistance.presentation.application.KbsuStudentAssistanceApp
import com.snakelord.pets.kbsustudentassistance.di.login.component.DaggerLoginComponent
import com.snakelord.pets.kbsustudentassistance.di.navigation.component.DaggerNavigationComponent
import com.snakelord.pets.kbsustudentassistance.domain.mapper.error.StudentErrorMapper
import com.snakelord.pets.kbsustudentassistance.presentation.login.LoginViewModel
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.NavigationViewModel
import java.lang.IllegalStateException
import javax.inject.Inject

class ViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                createLoginViewModel()
            }
            modelClass.isAssignableFrom(NavigationViewModel::class.java) -> {
                createNavigationViewModel()
            }
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

    private fun <T> createNavigationViewModel(): T {
        val applicationComponent = KbsuStudentAssistanceApp.applicationComponent
        val navigationComponent = DaggerNavigationComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
        val navigationViewModel = NavigationViewModel(
            navigationComponent.locationInteractor()
        )
        return navigationViewModel as T
    }
}