package com.snakelord.pets.kbsustudentassistance.schedule.domain.mapper

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.snakelord.pets.kbsustudentassistance.domain.mapper.schedule.LecturesDtoMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import org.junit.Test

class LecturesDtoMapperTest {

    private val lecturesDtoMapper = LecturesDtoMapper()

    @Test
    fun mapTest() {
        //Arrange
        val expectedResult = EXPECTED_LECTURES

        //Act
        val actualResult = lecturesDtoMapper.map(EXPECTED_DTO_LECTURES)

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    companion object {
        private val EXPECTED_DTO_LECTURES = listOf(
            LectureDto(
                lectureName = "Теория систем",
                teacher = "Иванов Петр Алексеевич",
                startTime = "9:00",
                endTime = "10:35",
                classroom = "100",
                instituteId = 1
            )
        )

        private val EXPECTED_LECTURES = listOf(
            Lecture(
                lectureName = "Теория систем",
                teacher = "Иванов Петр Алексеевич",
                startTime = "9:00",
                endTime = "10:35",
                classroom = "100",
                instituteId = 1
            )
        )
    }
}