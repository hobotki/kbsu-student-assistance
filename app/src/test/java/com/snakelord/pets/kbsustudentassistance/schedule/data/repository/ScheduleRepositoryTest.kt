package com.snakelord.pets.kbsustudentassistance.schedule.data.repository

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.ScheduleApi
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.schedule.ScheduleDao
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.student.StudentDao
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule.DayEntity
import com.snakelord.pets.kbsustudentassistance.data.exception.BadResponseException
import com.snakelord.pets.kbsustudentassistance.data.repository.schedule.ScheduleRepositoryImpl
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import java.io.InterruptedIOException
import java.net.UnknownHostException

class ScheduleRepositoryTest {

    private val scheduleApi: ScheduleApi = mockk()
    private val studentDao: StudentDao = mockk()
    private val scheduleDao: ScheduleDao = mockk()

    private val scheduleRepositoryImpl =
        ScheduleRepositoryImpl(scheduleApi, studentDao, scheduleDao)

    @Test
    fun getScheduleFromApiTest() {
        //Arrange
        every { studentDao.getSpecialityCode() } returns SPECIALITY_CODE
        every { scheduleApi.getSchedule(any()) } returns EXPECTED_RESULT_FROM_API
        val expectedResult = EXPECTED_RESULT_FROM_API

        //Act
        val actualResult = scheduleRepositoryImpl.getScheduleFromApi()
            .test()

        //Assert
        actualResult
            .assertValue(expectedResult)
            .dispose()
    }

    @Test
    fun getScheduleFromApiConnectionLost() {
        //Arrange
        every { studentDao.getSpecialityCode() } returns SPECIALITY_CODE
        val expectedException = UnknownHostException()
        every { scheduleApi.getSchedule(any()) } throws expectedException

        //Act
        val actualResult = scheduleRepositoryImpl.getScheduleFromApi()
            .test()

        //Assert
        actualResult
            .assertError(expectedException)
            .dispose()
    }

    @Test
    fun getScheduleFromApiTimeoutTest() {
        //Arrange
        every { studentDao.getSpecialityCode() } returns SPECIALITY_CODE
        val expectedException = InterruptedIOException()
        every { scheduleApi.getSchedule(any()) } throws expectedException

        //Act
        val actualResult = scheduleRepositoryImpl.getScheduleFromApi()
            .test()

        //Assert
        actualResult
            .assertError(expectedException)
            .dispose()
    }

    @Test
    fun getScheduleFromApiBadResponseTest() {
        //Arrange
        every { studentDao.getSpecialityCode() } returns SPECIALITY_CODE
        val expectedException = BadResponseException(RANDOM_ERROR_CODE)
        every { scheduleApi.getSchedule(any()) } throws expectedException

        //Act
        val actualResult = scheduleRepositoryImpl.getScheduleFromApi()
            .test()

        //Assert
        actualResult
            .assertError(expectedException)
            .dispose()
    }


    @Test
    fun getScheduleFromDBTest() {
        //Arrange
        every { scheduleDao.getSchedule() } returns Single.just(EXPECTED_RESULT_FROM_DB)
        val expectedResult = EXPECTED_RESULT_FROM_DB

        //Act
        val actualResult = scheduleRepositoryImpl.getScheduleFromDatabase()
            .test()

        //Assert
        actualResult
            .assertValue(expectedResult)
            .dispose()
    }

    @Test
    fun saveSchedule() {
        //Arrange
        every { scheduleDao.insertSchedule(any()) } returns Completable.complete()
        val expectedArguments = EXPECTED_RESULT_FROM_DB

        //Act
        val actualResult = scheduleRepositoryImpl.saveSchedule(expectedArguments)
            .test()

        //Assert
        actualResult
            .assertComplete()
            .dispose()
    }

    companion object {

        private const val SPECIALITY_CODE = "09.03.01-3"

        private const val RANDOM_ERROR_CODE = 757

        private val EXPECTED_RESULT_FROM_API = listOf(DayDto(
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

        private val EXPECTED_RESULT_FROM_DB = listOf(DayEntity(
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