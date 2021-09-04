package com.snakelord.pets.kbsustudentassistance.schedule.data.mapper

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.snakelord.pets.kbsustudentassistance.data.mapper.schedule.ScheduleResponseMapper
import com.snakelord.pets.kbsustudentassistance.domain.json_adapter.ScheduleJsonAdapter
import com.squareup.moshi.JsonDataException
import org.junit.Test
import java.io.EOFException

class ScheduleResponseMapperTest {

    private val scheduleJsonAdapter = ScheduleJsonAdapter()
    private val scheduleResponseMapper = ScheduleResponseMapper(scheduleJsonAdapter)

    @Test
    fun mapTest() {
        //Arrange
        val expectedResult = EXPECTED_RESULT

        //Act
        val actualResult = scheduleResponseMapper.map(EXPECTED_JSON)

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test(expected = EOFException::class)
    fun fromJsonETest() {
        //Act
        val actualResult = scheduleResponseMapper.map(EXPECTED_BAD_JSON)
    }

    @Test(expected = JsonDataException::class)
    fun fromJsonEETest() {
        //Act
        val actualResult = scheduleResponseMapper.map(EMPTY_RESPONSE)
    }

    companion object {
        private const val EXPECTED_JSON = """
            [
              {
                    "09.03.01-3": [
                    {
                         "dayName": "MONDAY",
                         "lectures": [
                            {
                                "lectureName": "Теория систем",
                                "teacher": "Иванов Петр Алексеевич",
                                "startTime": "9:00",
                                "endTime": "10:35",
                                "classroom": "100",
                                "instituteId": "1"
                            },
                            {
                                "lectureName": "Физика",
                                "teacher": "Витиевашко Андрей Феликсович",
                                "startTime": "10:45",
                                "endTime": "12:20",
                                "classroom": "405а",
                                "instituteId": "1"
                            }
                         ]
                    }
              ]      
              }
            ]  
        """

        private val EXPECTED_RESULT = listOf(DayDto(
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

        private const val EXPECTED_BAD_JSON = """
            [
              {
                    "09.03.01-3": [
                    {
                         "dayName": "MONDAY",
                         "lectures": [
                            {
                                "lectureName": "Теория систем",
                                "teacher": "Иванов Петр Алексеевич",
                                "startTime": "9:00",
                                "endTime": "10:35",
                                "classroom": "100",
                                "instituteId": "1"
                            },
                            {
                                "lectureName": "Физика",
                                "teacher": "Витиевашко Андрей Феликсович",
                                "startTime": "10:45",
                                "endTime": "12:20",
                                "classroom": "405а",
                                "instituteId": "1"
                            }
                         ]
                    }
              ]      
              }  
        """

        private const val EMPTY_RESPONSE = "[]"
    }
}