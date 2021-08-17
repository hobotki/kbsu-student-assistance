package com.snakelord.pets.kbsustudentassistance.di.login.component

import com.snakelord.pets.kbsustudentassistance.di.common.component.ApplicationComponent
import com.snakelord.pets.kbsustudentassistance.di.login.module.*
import com.snakelord.pets.kbsustudentassistance.domain.interactor.login.LoginInteractor
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.presentation.login.LoginFragment
import dagger.Component
import dagger.Subcomponent
import java.lang.Error

@Component(
    dependencies = [ApplicationComponent::class],
    modules = [LoginApiModule::class, LoginInteractorModule::class,
    LoginMapperModule::class, LoginRepositoryModule::class,
    LoginDaoModule::class]
)
interface LoginComponent {
    fun loginInteractor(): LoginInteractor
}