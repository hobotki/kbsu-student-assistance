package com.snakelord.pets.kbsustudentassistance.data.repository.settings

import com.snakelord.pets.kbsustudentassistance.data.datasource.database.Database
import com.snakelord.pets.kbsustudentassistance.domain.repository.settings.SettingsRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

/**
 * Реализация интерфейса [SettingsRepository]
 *
 * @property database база данных (необходима для очистки всех таблиц)
 *
 * @author Murad Luguev on 12-09-2021
 */
class SettingsRepositoryImpl @Inject constructor(
    private val database: Database
) : SettingsRepository {

    override fun logout(): Completable {
        return Completable.fromAction {
            database.clearAllTables()
        }
    }
}