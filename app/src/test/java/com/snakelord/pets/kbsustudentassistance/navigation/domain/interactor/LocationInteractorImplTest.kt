package com.snakelord.pets.kbsustudentassistance.navigation.domain.interactor

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule.DayEntity
import com.snakelord.pets.kbsustudentassistance.domain.interactor.navigation.LocationInteractorImpl
import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationPoint
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.domain.repository.navigation.LocationRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class LocationInteractorImplTest {

    private val locationRepository: LocationRepository = mockk()
    private val locationInteractor = LocationInteractorImpl(locationRepository)

    @Test
    fun getEnterPointsTest() {
        //Arrange
        every { locationRepository.getEnterPoints() } returns Single.just(LOCATION_EXPECTED_RESULT)
        val expectedResult = LOCATION_EXPECTED_RESULT

        //Act
        val actualResult = locationInteractor.getEnterPoints()
            .test()

        //Assert
        actualResult
            .assertResult(expectedResult)
            .dispose()
    }

    @Test
    fun getMainEnterPointTest() {
        //Arrange
        every { locationRepository.getMainEnterPoint() } returns MAIN_ENTRANCE_EXPECTED_RESULT
        val expectedResult = MAIN_ENTRANCE_EXPECTED_RESULT

        //Act
        val actualResult = locationInteractor.getMainEnterPoint()

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    companion object {
        private val EXPECTED_LECTURE = Lecture(
            "Физика",
            "",
            "9:00",
            "10:45",
            "156",
            1
        )

        private val LOCATION_EXPECTED_RESULT = listOf(
            LocationModel(
                R.string.main_entrance,
                1,
                LocationPoint(43.494545, 43.596298),
                listOf(EXPECTED_LECTURE)
            ),

            LocationModel(
                R.string.iopam,
                2,
                LocationPoint(43.495200, 43.597663)),

            LocationModel(
                R.string.information_centre,
                3,
                LocationPoint(43.496285, 43.595495)),

            LocationModel(
                R.string.fom,
                4,
                LocationPoint(43.496721, 43.594947)),

            LocationModel(
                R.string.sports_complex,
                5,
                LocationPoint(43.499001, 43.594629)),

            LocationModel(
                R.string.iodams,
                6,
                LocationPoint(43.497785, 43.594035)
            ),

            LocationModel(
                R.string.ioacd,
                7,
                LocationPoint(43.498807, 43.593489)
            ),

            LocationModel(
                R.string.eatf,
                8,
                LocationPoint(43.499304, 43.592977)
            )
        )

        private val MAIN_ENTRANCE_EXPECTED_RESULT = LocationModel(
            R.string.main_entrance,
            1,
            LocationPoint(43.494545, 43.596298)
        )

        private val EXPECTED_SCHEDULE = listOf(
            DayEntity(
                "MONDAY",
                listOf(
                    EXPECTED_LECTURE
                )
            )
        )
    }
}
