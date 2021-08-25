package com.snakelord.pets.kbsustudentassistance.domain.interactor.navigation

import com.snakelord.pets.kbsustudentassistance.data.model.location.LocationModel

/**
 * Интерактор для получения точек на карте
 *
 * @author Murad Luguev on 26-08-2021
 */
interface LocationInteractor {
    /**
     * Возвращает список [LocationModel], в котором содержаться точки для отображения на карте
     *
     * @return спиок [LocationModel]
     */
    fun getEnterPoints(): List<LocationModel>

    /**
     * Возвращает точку для отображения главного входа в ВУЗ
     *
     * @return экземпляр [LocationModel], который содержит информацию о
     * местоположении главного входа
     */
    fun getMainEnterPoint(): LocationModel
}