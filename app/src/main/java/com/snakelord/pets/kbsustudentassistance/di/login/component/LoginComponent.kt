package com.snakelord.pets.kbsustudentassistance.di.login.component

import com.snakelord.pets.kbsustudentassistance.di.common.component.ApplicationComponent
import com.snakelord.pets.kbsustudentassistance.di.login.module.*
import com.snakelord.pets.kbsustudentassistance.di.login.scope.LoginScope
import com.snakelord.pets.kbsustudentassistance.domain.interactor.login.LoginInteractor
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.model.OperationError
import dagger.Component

/**
 * Dagger-компонент для предоставления зависимостей, необходимых для экрана авторизации
 *
 * @author Murad Luguev on 27-08-2021
 */
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [
        LoginApiModule::class, LoginInteractorModule::class,
        LoginMapperModule::class, LoginRepositoryModule::class,
        LoginDaoModule::class]
)
@LoginScope
interface LoginComponent {
    /**
     * Функция, предоставляющая экземпляр [LoginInteractor]
     *
     * @return экземпляр [LoginInteractor]
     */
    fun loginInteractor(): LoginInteractor

    /**
     * Функция, предоставляющая экземпляр [Mapper]
     * для обработки ошибок во время авторизации
     *
     * @return экземпляр [Mapper] с типами [Throwable] и [OperationError]
     */
    fun studentErrorMapper(): Mapper<Throwable, OperationError>
}