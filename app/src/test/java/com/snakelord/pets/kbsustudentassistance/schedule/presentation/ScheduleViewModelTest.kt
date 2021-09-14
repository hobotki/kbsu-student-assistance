package com.snakelord.pets.kbsustudentassistance.schedule.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.snakelord.pets.kbsustudentassistance.domain.interactor.schedule.ScheduleInteractor
import com.snakelord.pets.kbsustudentassistance.domain.mapper.error.BaseErrorMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Day
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProviderTest
import com.snakelord.pets.kbsustudentassistance.presentation.schedule.ScheduleViewModel
import io.mockk.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ScheduleViewModelTest {
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    private val scheduleInteractor: ScheduleInteractor = mockk()
    private val schedulersProvider: SchedulersProvider = SchedulersProviderTest()
    private val baseErrorMapper = BaseErrorMapper()
    private lateinit var scheduleViewModel: ScheduleViewModel

    @Before
    fun setUp() {
        every { scheduleInteractor.getScheduleFromDatabase() } returns Single.just(EXPECTED_RESULT)
        scheduleViewModel = ScheduleViewModel(scheduleInteractor, schedulersProvider, baseErrorMapper)
    }

    @Test
    fun showScheduleByDayTest() {
        //Arrange
        val expectedSelectedIndex = 1

        //Act
        scheduleViewModel.showScheduleByDay(NEW_INDEX)

        //Assert
        Truth.assertThat(scheduleViewModel.selectedIndex).isEqualTo(expectedSelectedIndex)
    }

    @Test
    fun loadScheduleFromApi() {
        //Arrange
        every { scheduleInteractor.getScheduleFromApi() } returns Single.just(
            EXPECTED_RESULT_FROM_API
        )
        every { scheduleInteractor.saveSchedule(any()) } returns Completable.complete()

        //Act
        scheduleViewModel =
            ScheduleViewModel(scheduleInteractor, schedulersProvider, baseErrorMapper)
        every { scheduleInteractor.getScheduleFromDatabase() } returns Single.just(EXPECTED_RESULT)

        //Assert
    }

    companion object {
        private val EXPECTED_RESULT = listOf(
            Day(
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
            ),
            Day(
                dayName = "TUESDAY",
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

        private val EXPECTED_RESULT_FROM_API = listOf(
            DayDto(
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
            ),
            DayDto(
                dayName = "TUESDAY",
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

        private const val NEW_INDEX = 1
    }
}