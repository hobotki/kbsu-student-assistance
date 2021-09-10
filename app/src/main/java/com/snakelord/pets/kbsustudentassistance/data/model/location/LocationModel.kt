package com.snakelord.pets.kbsustudentassistance.data.model.location

import androidx.annotation.StringRes
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture

/**
 * Модель для отображения входа в ВУЗ
 *
 * @property enterRes строковый ресурс для отображения названия корпуса
 * @property locationPoint экземпляр класса [LocationModel] для отображения точки на карте
 * @property lectures лекции, которые проходят в этом корпусе, по-умолчанию [emptyList]
 *
 * @author Murad Luguev on 26-08-2021
 */
data class LocationModel(
    @StringRes
    val enterRes: Int,

    val instituteId: Int,

    val locationPoint: LocationPoint,

    var lectures: List<Lecture> = emptyList()
)
