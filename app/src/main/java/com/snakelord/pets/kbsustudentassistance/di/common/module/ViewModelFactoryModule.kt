package com.snakelord.pets.kbsustudentassistance.di.common.module

import androidx.lifecycle.ViewModelProvider
import com.snakelord.pets.kbsustudentassistance.presentation.common.viewmodel.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Dagger-модуль для предоставления [ViewModelProvider.Factory]
 *
 * @author Murad Luguev on 27-08-2021
 */
@Module
interface ViewModelFactoryModule {
    /**
     * Функция, которая связывает заданную реализацию с интерфейсом [ViewModelProvider.Factory]
     *
     * @param viewModelFactory реализация интерфейса [ViewModelProvider.Factory]
     *
     * @return экземпляр [ViewModelProvider.Factory]
     */
    @Binds
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}