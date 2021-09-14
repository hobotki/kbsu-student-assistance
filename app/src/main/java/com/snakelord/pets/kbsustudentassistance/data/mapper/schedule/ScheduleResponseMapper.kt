package com.snakelord.pets.kbsustudentassistance.data.mapper.schedule

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.domain.json_adapter.schedule.ScheduleJsonAdapter
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import javax.inject.Inject

/**
 * Маппер для преобразования ответа с сервера в расписание
 *
 * @property scheduleJsonAdapter адаптер для получения [List]<[DayDto]> из JSON
 *
 * @author Murad Luguev on 01-09-2021
 */
class ScheduleResponseMapper @Inject constructor(
    private val scheduleJsonAdapter: ScheduleJsonAdapter
) : Mapper<String, @JvmSuppressWildcards List<DayDto>> {

    override fun map(input: String): List<DayDto> {
        return scheduleJsonAdapter.fromJson(input)!!
    }
}