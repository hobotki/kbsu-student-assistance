package com.snakelord.pets.kbsustudentassistance.schedule.domain.interactor

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule.DayEntity
import com.snakelord.pets.kbsustudentassistance.data.exception.BadResponseException
import com.snakelord.pets.kbsustudentassistance.domain.interactor.schedule.ScheduleInteractorImpl
import com.snakelord.pets.kbsustudentassistance.domain.mapper.schedule.DaysDtoMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.schedule.DaysEntityMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.schedule.LecturesDtoMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Day
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.domain.repository.schedule.ScheduleRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import java.io.InterruptedIOException
import java.net.UnknownHostException

class ScheduleInteractorTest {

    private val scheduleRepository: ScheduleRepository = mockk()
    private val lecturesDtoMapper = LecturesDtoMapper()
    private val daysDtoMapper = DaysDtoMapper(lecturesDtoMapper)
    private val daysEntityMapper = DaysEntityMapper()
    private val scheduleInteractorImpl =
        ScheduleInteractorImpl(scheduleRepository, daysDtoMapper, daysEntityMapper)

    @Test
    fun getScheduleFromApiTest() {
        //Arrange
        every { scheduleRepository.getScheduleFromApi() } returns
                Single.just(EXPECTED_RESULT_FROM_API)
        val expectedResult = EXPECTED_RESULT_FROM_API

        //Act
        val actualResult = scheduleInteractorImpl.getScheduleFromApi()
            .test()

        //Assert
        actualResult
            .assertValue(expectedResult)
            .dispose()
    }

    @Test
    fun getScheduleFromApiConnectionLost() {
        //Arrange
        val expectedException = UnknownHostException()
        every { scheduleRepository.getScheduleFromApi() } returns Single.error(expectedException)

        //Act
        val actualResult = scheduleInteractorImpl.getScheduleFromApi()
            .test()

        //Assert
        actualResult
            .assertError(expectedException)
            .dispose()
    }

    @Test
    fun getScheduleFromApiTimeoutTest() {
        //Arrange
        val expectedException = InterruptedIOException()
        every { scheduleRepository.getScheduleFromApi() } returns Single.error(expectedException)

        //Act
        val actualResult = scheduleInteractorImpl.getScheduleFromApi()
            .test()

        //Assert
        actualResult
            .assertError(expectedException)
            .dispose()
    }

    @Test
    fun getScheduleFromApiBadResponseTest() {
        //Arrange
        val expectedException = BadResponseException(RANDOM_ERROR_CODE)
        every { scheduleRepository.getScheduleFromApi() } returns Single.error(expectedException)

        //Act
        val actualResult = scheduleInteractorImpl.getScheduleFromApi()
            .test()

        //Assert
        actualResult
            .assertError(expectedException)
            .dispose()
    }


    @Test
    fun getScheduleFromDBTest() {
        //Arrange
        every { scheduleRepository.getScheduleFromDatabase() } returns
                Single.just(EXPECTED_RESULT_FROM_DB)
        val expectedResult = EXPECTED_RESULT

        //Act
        val actualResult = scheduleInteractorImpl.getScheduleFromDatabase()
            .test()

        //Assert
        actualResult
            .assertValue(expectedResult)
            .dispose()
    }

    @Test
    fun saveSchedule() {
        //Arrange
        every { scheduleRepository.saveSchedule(any()) } returns Completable.complete()
        val expectedArguments = EXPECTED_RESULT_FROM_API

        //Act
        val actualResult = scheduleInteractorImpl
            .saveSchedule(expectedArguments)
            .test()

        //Assert
        actualResult
            .assertComplete()
            .dispose()
    }

    companion object {

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

        private val EXPECTED_RESULT = listOf(Day(
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