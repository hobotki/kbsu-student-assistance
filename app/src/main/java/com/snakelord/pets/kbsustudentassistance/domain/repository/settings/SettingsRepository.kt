package com.snakelord.pets.kbsustudentassistance.domain.repository.settings

import io.reactivex.rxjava3.core.Completable

/**
 * Репозиторий для работы с настройками студента
 *
 * @author Murad Luguev on 12-09-2021
 */
interface SettingsRepository {
    /**
     * Функция для выхода из аккаунта студента
     *
     * @return результат операции типа [Completable]
     */
    fun logout(): Completable
}