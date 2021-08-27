package com.snakelord.pets.kbsustudentassistance.di.navigation.module

import com.snakelord.pets.kbsustudentassistance.data.repository.navigation.LocationRepositoryImpl
import com.snakelord.pets.kbsustudentassistance.domain.interactor.navigation.LocationInteractor
import com.snakelord.pets.kbsustudentassistance.domain.interactor.navigation.LocationInteractorImpl
import com.snakelord.pets.kbsustudentassistance.domain.repository.navigation.LocationRepository
import dagger.Binds
import dagger.Module

/**
 * Dagger-модуль для инъекции зависимостей в NavigationViewModel
 *
 * @author Murad Luguev on 26-08-2021
 */
@Module
interface NavigationModule {
    /**
     * Связывает интерфейс [LocationRepository] с его реализацией
     *
     * @param locationRepositoryImpl реализация интерфейса [LocationRepository]
     * @return экземпрляр [LocationRepository]
     */
    @Binds
    fun bindsLocationRepository(locationRepositoryImpl: LocationRepositoryImpl): LocationRepository

    /**
     * Связывает интерфейс [LocationInteractor] с его реализацией
     *
     * @param locationInteractorImpl реализация интерфейса [LocationInteractor]
     * @return экземпрляр [LocationInteractor]
     */
    @Binds
    fun bindsLocationInteractor(locationInteractorImpl: LocationInteractorImpl): LocationInteractor
}