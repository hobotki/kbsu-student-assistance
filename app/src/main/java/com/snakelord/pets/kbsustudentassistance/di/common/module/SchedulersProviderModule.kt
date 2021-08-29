package com.snakelord.pets.kbsustudentassistance.di.common.module

import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProviderImpl
import dagger.Binds
import dagger.Module

/**
 * Dagger-модуль для предоставления [SchedulersProvider]
 *
 * @author Murad Luguev on 27-08-2021
 */
@Module
interface SchedulersProviderModule {
    /**
     * Функция, которая связвывает заданную реализацию с интерфейсом [SchedulersProvider]
     *
     * @param schedulersProviderImpl реализация интерфейса [SchedulersProvider]
     *
     * @return экземпляр [SchedulersProvider]
     */
    @Binds
    fun bindsSchedulerProvider(schedulersProviderImpl: SchedulersProviderImpl): SchedulersProvider
}