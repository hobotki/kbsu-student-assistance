package com.snakelord.pets.kbsustudentassistance.schedule.json_adapter

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.snakelord.pets.kbsustudentassistance.domain.json_adapter.schedule.ScheduleJsonAdapter
import com.squareup.moshi.JsonDataException
import org.junit.Test
import java.io.EOFException

class ScheduleJsonAdapterTest {

    private val scheduleJsonAdapter = ScheduleJsonAdapter()

    @Test
    fun fromJsonTest() {
        //Arrange
        val expectedResult = EXPECTED_RESULT

        //Act
        val actualResult = scheduleJsonAdapter.fromJson(EXPECTED_JSON)

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test(expected = EOFException::class)
    fun fromJsonBadJsonTest() {
        //Act
        val actualResult = scheduleJsonAdapter.fromJson(EXPECTED_BAD_JSON)
    }

    @Test(expected = JsonDataException::class)
    fun fromJsonEmptyJsonTest() {
        //Act
        val actualResult = scheduleJsonAdapter.fromJson(EMPTY_RESPONSE)
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