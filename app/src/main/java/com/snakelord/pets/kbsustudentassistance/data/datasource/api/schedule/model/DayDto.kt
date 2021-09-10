package com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model

/**
 * Модель дня недели
 *
 * @property dayName название дня недели
 * @property lectures лекции, которые проходят в этот день, [List] с типом [LectureDto]
 *
 * @author Murad Luguev on 01-09-2021
 */
data class DayDto(
    val dayName: String,

    val lectures: List<LectureDto>
)
