package com.snakelord.pets.kbsustudentassistance.domain.model.schedule

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto

/**
 * Модель дня недели
 *
 * @property dayName название дня недели
 * @property lectures лекции, которые проходят в этот день, [List] с типом [LectureDto]
 *
 * @author Murad Luguev on 01-09-2021
 */
data class Day(
    val dayName: String,

    val lectures: List<Lecture>
)