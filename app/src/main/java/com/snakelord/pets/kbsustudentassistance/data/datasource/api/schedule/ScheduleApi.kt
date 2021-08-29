package com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto

/**
 * Интерфейс для взаимодействия с API для получения расписания студента
 *
 * @author Murad Luguev on 01-09-2021
 */
interface ScheduleApi {

    /**
     * Функция для получения расписания по заданному коду специальности
     *
     * @param specialityCode код специальности студента
     *
     * @return расписание типа [List]<[DayDto]>
     */
    fun getSchedule(specialityCode: String): List<DayDto>
}