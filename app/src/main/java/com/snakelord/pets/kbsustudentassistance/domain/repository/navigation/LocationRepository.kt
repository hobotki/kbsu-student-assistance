package com.snakelord.pets.kbsustudentassistance.domain.repository.navigation

import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationModel
import io.reactivex.rxjava3.core.Single

/**
 * Репозиторий для получения локаций для отображения на карте
 *
 * @author Murad Luguev on 26-08-2021
 */
interface LocationRepository {
    /**
     * Возвращает список [LocationModel], в котором содержаться точки для отображения на карте
     *
     * @return список [LocationModel] с лекциями, которые проходят в предоставленных корпусах
     */
    fun getEnterPoints(): Single<List<LocationModel>>

    /**
     * Возвращает точку для отображения главного входа в ВУЗ
     *
     * @return экземпляр [LocationModel], который содержит информацию о
     * местоположении главного входа
     */
    fun getMainEnterPoint(): LocationModel
}