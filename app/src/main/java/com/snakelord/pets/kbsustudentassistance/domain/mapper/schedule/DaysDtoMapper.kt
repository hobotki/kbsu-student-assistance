package com.snakelord.pets.kbsustudentassistance.domain.mapper.schedule

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule.DayEntity
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import javax.inject.Inject

/**
 * Маппер для преобразования [List]<[DayDto]> в [List]<[DayEntity]>
 *
 * @property lecturesMapper маппер лекций
 *
 * @author Murad Luguev on 01-09-2021
 */
class DaysDtoMapper @Inject constructor(
    private val lecturesMapper: Mapper<List<LectureDto>, List<Lecture>>
) : Mapper<@JvmSuppressWildcards List<DayDto>, @JvmSuppressWildcards List<DayEntity>> {

    override fun map(input: List<DayDto>): List<DayEntity> {
        return input.map { dayDto ->
            DayEntity(
                dayName = dayDto.dayName,
                lectures = lecturesMapper.map(dayDto.lectures)
            )
        }
    }

}