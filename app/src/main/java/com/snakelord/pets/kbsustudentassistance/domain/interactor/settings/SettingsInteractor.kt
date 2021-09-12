package com.snakelord.pets.kbsustudentassistance.domain.interactor.settings

import io.reactivex.rxjava3.core.Completable

/**
 * Интерактор для взаоимодействия с настройками
 *
 * @author Murad Luguev on 12-09-2021
 */
interface SettingsInteractor {
    /**
     * Функция для выхода из приложения
     *
     * @return результат операции типа [Completable]
     */
    fun logout(): Completable
}