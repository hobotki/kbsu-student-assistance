package com.snakelord.pets.kbsustudentassistance.presentation.common.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.snakelord.pets.kbsustudentassistance.data.repository.pass.PassRepositoryImpl
import com.snakelord.pets.kbsustudentassistance.di.login.component.DaggerLoginComponent
import com.snakelord.pets.kbsustudentassistance.di.navigation.component.DaggerNavigationComponent
import com.snakelord.pets.kbsustudentassistance.di.pass.component.DaggerPassComponent
import com.snakelord.pets.kbsustudentassistance.di.schedule.component.DaggerScheduleComponent
import com.snakelord.pets.kbsustudentassistance.domain.interactor.pass.PassInteractorImpl
import com.snakelord.pets.kbsustudentassistance.domain.mapper.pass.StudentDataMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.pass.StudentMapper
import com.snakelord.pets.kbsustudentassistance.presentation.application.KbsuStudentAssistanceApp
import com.snakelord.pets.kbsustudentassistance.presentation.login.LoginViewModel
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.NavigationViewModel
import com.snakelord.pets.kbsustudentassistance.presentation.pass.PassViewModel
import com.snakelord.pets.kbsustudentassistance.presentation.schedule.ScheduleViewModel
import javax.inject.Inject

/**
 * Фабрика для создания всех ViewModel в приложении
 *
 * @author Murad Luguev on 27-08-2021
 */
class ViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    private val applicationComponent = KbsuStudentAssistanceApp.applicationComponent

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                createLoginViewModel() as T
            }
            modelClass.isAssignableFrom(NavigationViewModel::class.java) -> {
                createNavigationViewModel() as T
            }
            modelClass.isAssignableFrom(ScheduleViewModel::class.java) -> {
                createScheduleViewModel() as T
            }
            modelClass.isAssignableFrom(PassViewModel::class.java) -> {
                createPassViewModel() as T
            }
            else -> throw IllegalStateException()
        }
    }

    private fun createScheduleViewModel(): ScheduleViewModel {
        val scheduleComponent = DaggerScheduleComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
        return ScheduleViewModel(
            scheduleComponent.scheduleInteractor(),
            applicationComponent.schedulersProvider()
        )
    }

    private fun createLoginViewModel(): LoginViewModel {
        val loginComponent = DaggerLoginComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
        return LoginViewModel(
            loginComponent.loginInteractor(),
            applicationComponent.schedulersProvider(),
            loginComponent.studentErrorMapper()
        )
    }

    private fun createNavigationViewModel(): NavigationViewModel {
        val navigationComponent = DaggerNavigationComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
        return NavigationViewModel(
            navigationComponent.locationInteractor(),
            applicationComponent.schedulersProvider(),
            applicationComponent.application()
        )
    }

    private fun createPassViewModel(): PassViewModel {
        val passComponent = DaggerPassComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
        return PassViewModel(
            passComponent.passInteractor(),
            applicationComponent.schedulersProvider()
        )
    }
}
