package com.snakelord.pets.kbsustudentassistance.domain.mapper.schedule

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import javax.inject.Inject

/**
 * Маппер для преобразования списка [LectureDto] в список [Lecture]
 *
 * @author Murad Luguev on 01-09-2021
 */
class LecturesDtoMapper @Inject constructor() :
    Mapper<@JvmSuppressWildcards List<LectureDto>, @JvmSuppressWildcards List<Lecture>> {

    override fun map(input: List<LectureDto>): List<Lecture> {
        return input.map { lectureDto ->
            Lecture(
                lectureName = lectureDto.lectureName,
                teacher = lectureDto.teacher,
                startTime = lectureDto.startTime,
                endTime = lectureDto.endTime,
                classroom = lectureDto.classroom
            )
        }
    }

}