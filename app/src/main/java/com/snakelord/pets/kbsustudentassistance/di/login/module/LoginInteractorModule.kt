package com.snakelord.pets.kbsustudentassistance.di.login.module

import com.snakelord.pets.kbsustudentassistance.domain.interactor.login.LoginInteractor
import com.snakelord.pets.kbsustudentassistance.domain.interactor.login.LoginInteractorImpl
import dagger.Binds
import dagger.Module

/**
 * Dagger-модуль для предоставления [LoginInteractor]
 * для работы с данными студента
 *
 * @author Murad Luguev on 27-08-2021
 */
@Module
interface LoginInteractorModule {
    /**
     * Функция, которая связвывает заданную реализацию с интерфейсом [LoginInteractor]
     *
     * @param loginInteractorImpl реализация интерфейса [LoginInteractor]
     *
     * @return экземпляр [LoginInteractor]
     */
    @Binds
    fun bindsLoginInteractor(loginInteractorImpl: LoginInteractorImpl): LoginInteractor
}