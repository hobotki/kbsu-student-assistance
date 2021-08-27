package com.snakelord.pets.kbsustudentassistance.data.model.location

import androidx.annotation.StringRes

/**
 * Модель для отображения входа в ВУЗ
 *
 * @property enterRes строковый ресурс для отображения названия корпуса
 * @property locationPoint экземпляр класса [LocationModel] для отображения точки на карте
 *
 * @author Murad Luguev on 26-08-2021
 */
data class LocationModel(
    @StringRes
    val enterRes: Int,

    val locationPoint: LocationPoint
)
