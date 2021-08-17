package com.snakelord.pets.kbsustudentassistance.di.login.module

import com.snakelord.pets.kbsustudentassistance.domain.interactor.login.LoginInteractor
import com.snakelord.pets.kbsustudentassistance.domain.interactor.login.LoginInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface LoginInteractorModule {
    @Binds
    fun bindsLoginInteractor(loginInteractorImpl: LoginInteractorImpl): LoginInteractor
}