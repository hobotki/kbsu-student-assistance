package com.snakelord.pets.kbsustudentassistance.domain.interactor.schedule

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule.DayEntity
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Day
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

/**
 * Интерактор для взаимодействия с расписанием студента
 *
 * @author Murad Luguev on 01-09-2021
 */
interface ScheduleInteractor {
    /**
     * Функция для получения расписания с API
     *
     * @return расписание типа [Single]<[List]<[DayDto]>>
     */
    fun getScheduleFromApi(): Single<List<DayDto>>

    fun getScheduleFromDatabase(): Single<List<Day>>

    fun saveSchedule(schedule: List<DayDto>): Completable
}