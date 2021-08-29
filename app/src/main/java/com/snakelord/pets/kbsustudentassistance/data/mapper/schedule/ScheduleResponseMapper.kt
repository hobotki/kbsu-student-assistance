package com.snakelord.pets.kbsustudentassistance.data.mapper.schedule

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.domain.json_adapter.ScheduleJsonAdapter
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import javax.inject.Inject

class ScheduleResponseMapper @Inject constructor(
    private val scheduleJsonAdapter: ScheduleJsonAdapter
) : Mapper<String, @JvmSuppressWildcards List<DayDto>> {

    override fun map(input: String): List<DayDto> {
        return scheduleJsonAdapter.fromJson(input)!!
    }
}