package com.snakelord.pets.kbsustudentassistance.schedule.domain.mapper

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule.DayEntity
import com.snakelord.pets.kbsustudentassistance.domain.mapper.schedule.DaysDtoMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.schedule.LecturesDtoMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import org.junit.Test

class DaysDtoMapperTest {

    private val lecturesMapper = LecturesDtoMapper()
    private val daysDtoMapper = DaysDtoMapper(lecturesMapper)

    @Test
    fun testMap() {
        //Arrange
        val expectedResult = EXPECTED_DAYS_ENTITY

        //Act
        val actualResult = daysDtoMapper.map(EXPECTED_DAYS_DTO)

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    companion object {
        private val EXPECTED_DAYS_DTO = listOf(DayDto(
                dayName = "MONDAY",
                lectures = listOf(
                    LectureDto(
                        lectureName = "Теория систем",
                        teacher = "Иванов Петр Алексеевич",
                        startTime = "9:00",
                        endTime = "10:35",
                        classroom = "100",
                        instituteId = 1
                    ),
                    LectureDto(
                        lectureName = "Физика",
                        teacher = "Витиевашко Андрей Феликсович",
                        startTime = "10:45",
                        endTime = "12:20",
                        classroom = "405а",
                        instituteId = 1
                    )
                )
            )
        )

        private val EXPECTED_DAYS_ENTITY = listOf(DayEntity(
                dayName = "MONDAY",
                lectures = listOf(
                    Lecture(
                        lectureName = "Теория систем",
                        teacher = "Иванов Петр Алексеевич",
                        startTime = "9:00",
                        endTime = "10:35",
                        classroom = "100",
                        instituteId = 1
                    ),
                    Lecture(
                        lectureName = "Физика",
                        teacher = "Витиевашко Андрей Феликсович",
                        startTime = "10:45",
                        endTime = "12:20",
                        classroom = "405а",
                        instituteId = 1
                    )
                )
            )
        )
    }
}