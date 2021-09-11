package com.snakelord.pets.kbsustudentassistance.di.settings.module

import com.snakelord.pets.kbsustudentassistance.data.repository.settings.SettingsRepositoryImpl
import com.snakelord.pets.kbsustudentassistance.domain.interactor.settings.SettingsInteractor
import com.snakelord.pets.kbsustudentassistance.domain.interactor.settings.SettingsInteractorImpl
import com.snakelord.pets.kbsustudentassistance.domain.repository.settings.SettingsRepository
import dagger.Binds
import dagger.Module

@Module
interface SettingsModule {
    @Binds
    fun bindsSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository

    @Binds
    fun bindsSettingsInteractor(settingsInteractorImpl: SettingsInteractorImpl): SettingsInteractor
}