package com.snakelord.pets.kbsustudentassistance.di.navigation.component

import com.snakelord.pets.kbsustudentassistance.di.common.component.ApplicationComponent
import com.snakelord.pets.kbsustudentassistance.di.navigation.module.NavigationModule
import com.snakelord.pets.kbsustudentassistance.domain.interactor.navigation.LocationInteractor
import dagger.Component

/**
 * Dagger-компонент для инъекции зависимостей в NavigationViewModel
 *
 * @author Murad Luguev on 26-08-2021
 */
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [NavigationModule::class])
interface NavigationComponent {
    /**
     * Возвращает интерактор для отображения точек входа на карте
     *
     * @return экземпляр [LocationInteractor]
     */
    fun locationInteractor(): LocationInteractor
}