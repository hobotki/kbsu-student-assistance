package com.snakelord.pets.kbsustudentassistance.domain.interactor.settings

import com.snakelord.pets.kbsustudentassistance.domain.repository.settings.SettingsRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

/**
 * Реализация интерфейса [SettingsInteractor]
 *
 * @property settingsRepository репозиторий для изменения настроек
 *
 * @author Murad Luguev on 12-09-2021
 */
class SettingsInteractorImpl @Inject constructor(
    private val settingsRepository: SettingsRepository
) : SettingsInteractor {

    override fun logout(): Completable {
        return settingsRepository.logout()
    }
}