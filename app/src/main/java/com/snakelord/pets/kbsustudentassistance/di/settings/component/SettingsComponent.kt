package com.snakelord.pets.kbsustudentassistance.di.settings.component

import com.snakelord.pets.kbsustudentassistance.di.common.component.ApplicationComponent
import com.snakelord.pets.kbsustudentassistance.di.settings.module.SettingsModule
import com.snakelord.pets.kbsustudentassistance.di.settings.scope.SettingsScope
import com.snakelord.pets.kbsustudentassistance.domain.interactor.settings.SettingsInteractor
import dagger.Component

@Component(
    dependencies = [ApplicationComponent::class],
    modules = [SettingsModule::class]
)
@SettingsScope
interface SettingsComponent {
    fun settingsInteractor(): SettingsInteractor
}