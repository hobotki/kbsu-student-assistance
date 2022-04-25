package com.snakelord.pets.kbsustudentassistance.di.login.component

import com.snakelord.pets.kbsustudentassistance.di.login.module.LoginApiModule
import com.snakelord.pets.kbsustudentassistance.di.login.module.LoginInteractorModule
import com.snakelord.pets.kbsustudentassistance.di.login.module.LoginMapperModule
import com.snakelord.pets.kbsustudentassistance.di.login.module.LoginRepositoryModule
import com.snakelord.pets.kbsustudentassistance.di.login.scope.LoginScope
import com.snakelord.pets.kbsustudentassistance.domain.interactor.login.LoginInteractor
import dagger.Subcomponent

/**
 * Dagger-компонент для предоставления зависимостей, необходимых для экрана авторизации
 *
 * @author Murad Luguev on 27-08-2021
 */
@Subcomponent(
    modules = [
        LoginApiModule::class, LoginInteractorModule::class,
        LoginMapperModule::class, LoginRepositoryModule::class]
)
@LoginScope
interface LoginComponent {
    /**
     * Функция, предоставляющая экземпляр [LoginInteractor]
     *
     * @return экземпляр [LoginInteractor]
     */
    fun loginInteractor(): LoginInteractor
}
