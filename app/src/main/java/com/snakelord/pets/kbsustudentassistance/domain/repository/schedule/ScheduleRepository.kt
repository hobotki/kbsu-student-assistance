package com.snakelord.pets.kbsustudentassistance.domain.repository.schedule

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule.DayEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

/**
 * Репозиторий для получения расписания
 *
 * @author Murad Luguev on 01-09-2021
 */
interface ScheduleRepository {
    /**
     * Функция для загрузки расписания с API
     *
     * @return расписание типа [Single]<[List]<[DayDto]>>
     */
    fun getScheduleFromApi(): Single<List<DayDto>>

    fun getScheduleFromDatabase(): Single<List<DayEntity>>

    fun saveSchedule(schedule: List<DayEntity>): Completable
}