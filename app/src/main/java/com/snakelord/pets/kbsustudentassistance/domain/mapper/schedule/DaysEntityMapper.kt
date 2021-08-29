package com.snakelord.pets.kbsustudentassistance.domain.mapper.schedule

import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule.DayEntity
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Day
import javax.inject.Inject

class DaysEntityMapper @Inject constructor() :
    Mapper<List<@JvmSuppressWildcards DayEntity>, @JvmSuppressWildcards List<Day>> {

    override fun map(input: List<DayEntity>): List<Day> {
        return input.map { dayEntity ->
            Day(
                dayName = dayEntity.dayName,
                lectures = dayEntity.lectures
            )
        }
    }
}