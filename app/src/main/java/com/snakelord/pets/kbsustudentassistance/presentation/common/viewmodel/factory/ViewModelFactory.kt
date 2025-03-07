package com.snakelord.pets.kbsustudentassistance.presentation.common.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.snakelord.pets.kbsustudentassistance.di.navigation.component.DaggerNavigationComponent
import com.snakelord.pets.kbsustudentassistance.di.pass.component.DaggerPassComponent
import com.snakelord.pets.kbsustudentassistance.di.pass.component.PassComponent
import com.snakelord.pets.kbsustudentassistance.di.schedule.component.DaggerScheduleComponent
import com.snakelord.pets.kbsustudentassistance.di.settings.component.DaggerSettingsComponent
import com.snakelord.pets.kbsustudentassistance.presentation.application.KbsuStudentAssistanceApp
import com.snakelord.pets.kbsustudentassistance.presentation.login.LoginViewModel
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.NavigationViewModel
import com.snakelord.pets.kbsustudentassistance.presentation.pass.PassViewModel
import com.snakelord.pets.kbsustudentassistance.presentation.schedule.ScheduleViewModel
import com.snakelord.pets.kbsustudentassistance.presentation.settings.SettingsViewModel
import javax.inject.Inject

/**
 * Фабрика для создания всех ViewModel в приложении
 *
 * @author Murad Luguev on 27-08-2021
 */
class ViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

    private val applicationComponent = KbsuStudentAssistanceApp.applicationComponent

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass) {
            LoginViewModel::class.java -> createLoginViewModel()

            NavigationViewModel::class.java -> createNavigationViewModel()

            ScheduleViewModel::class.java -> createScheduleViewModel()

            PassViewModel::class.java -> createPassViewModel()

            SettingsViewModel::class.java -> createSettingsViewModel()

            else -> throw IllegalStateException()
        }
        return viewModel as T
    }

    private fun createLoginViewModel(): LoginViewModel {
        val loginComponent = applicationComponent.loginComponent()
        return LoginViewModel(
            loginComponent.loginInteractor(),
            applicationComponent.schedulersProvider(),
            applicationComponent.baseErrorMapper()
        )
    }

    private fun createScheduleViewModel(): ScheduleViewModel {
        val scheduleComponent = DaggerScheduleComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
        return ScheduleViewModel(
            scheduleComponent.scheduleInteractor(),
            applicationComponent.schedulersProvider(),
            applicationComponent.baseErrorMapper()
        )
    }

    private fun createNavigationViewModel(): NavigationViewModel {
        val navigationComponent = DaggerNavigationComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
        return NavigationViewModel(
            navigationComponent.locationInteractor(),
            applicationComponent.schedulersProvider(),
            applicationComponent.themeChanger(),
            applicationComponent.application()
        )
    }

    private fun createPassViewModel(): PassViewModel {
        val passComponent: PassComponent = DaggerPassComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
        return PassViewModel(
            passComponent.passInteractor(),
            applicationComponent.schedulersProvider()
        )
    }

    private fun createSettingsViewModel(): SettingsViewModel {
        val settingsComponent = DaggerSettingsComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
        return SettingsViewModel(
            settingsComponent.settingsInteractor(),
            applicationComponent.schedulersProvider(),
            applicationComponent.themeChanger()
        )
    }
}
