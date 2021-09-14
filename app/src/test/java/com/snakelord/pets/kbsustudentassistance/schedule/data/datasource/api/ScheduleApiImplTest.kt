package com.snakelord.pets.kbsustudentassistance.schedule.data.datasource.api

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.ScheduleApiImpl
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.snakelord.pets.kbsustudentassistance.data.exception.BadResponseException
import com.snakelord.pets.kbsustudentassistance.data.mapper.schedule.ScheduleResponseMapper
import com.snakelord.pets.kbsustudentassistance.domain.json_adapter.schedule.ScheduleJsonAdapter
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import io.mockk.every
import io.mockk.mockk
import okhttp3.OkHttpClient
import okhttp3.Response
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.io.InterruptedIOException
import java.net.UnknownHostException

class ScheduleApiImplTest {

    private val okHttpClient: OkHttpClient = mockk()
    private val response: Response = mockk()
    private val scheduleJsonAdapter = ScheduleJsonAdapter()
    private val responseMapper: Mapper<String, List<DayDto>> =
        ScheduleResponseMapper(scheduleJsonAdapter)
    private val scheduleAmiImpl = ScheduleApiImpl(okHttpClient, responseMapper)

    @Before
    fun setUp() {
        every { okHttpClient.newCall(any()).execute() } returns response
    }

    @Test
    fun getScheduleTest() {
        //Arrange
        every { response.isSuccessful } returns true
        every { response.body!!.string() } returns EXPECTED_RESPONSE_BODY
        val expectedResult = EXPECTED_RESULT

        //Act
        val actualResult = scheduleAmiImpl.getSchedule(SPECIALITY_CODE)

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test(expected = BadResponseException::class)
    fun scheduleNotFoundTest() {
        //Arrange
        every { response.isSuccessful } returns true
        every { response.body!!.string() } returns EMPTY_RESPONSE

        //Act
        scheduleAmiImpl.getSchedule(SPECIALITY_CODE)
    }

    @Test(expected = IOException::class)
    fun connectionLostTest() {
        //Arrange
        every { okHttpClient.newCall(any()).execute() } throws UnknownHostException()

        //Act
        scheduleAmiImpl.getSchedule(SPECIALITY_CODE)
    }

    @Test(expected = InterruptedIOException::class)
    fun connectionTimeoutTest() {
        //Arrange
        every { okHttpClient.newCall(any()).execute() } throws InterruptedIOException()

        //Act
        scheduleAmiImpl.getSchedule(SPECIALITY_CODE)
    }

    @Test(expected = BadResponseException::class)
    fun badResponseTest() {
        //Arrange
        every { response.code } returns RANDOM_ERROR_CODE
        every { response.isSuccessful } returns false

        //Act
        scheduleAmiImpl.getSchedule(SPECIALITY_CODE)
    }

    companion object {

        private const val RANDOM_ERROR_CODE = 757

        private const val EMPTY_RESPONSE = "[]"

        private const val SPECIALITY_CODE = "09.03.01-3"

        private const val EXPECTED_RESPONSE_BODY = """
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
    }
}