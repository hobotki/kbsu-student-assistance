package com.snakelord.pets.kbsustudentassistance.navigation.domain

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.data.model.location.LocationPoint
import com.snakelord.pets.kbsustudentassistance.domain.interactor.navigation.LocationInteractor
import com.snakelord.pets.kbsustudentassistance.domain.interactor.navigation.LocationInteractorImpl
import com.snakelord.pets.kbsustudentassistance.domain.repository.navigation.LocationRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class LocationInteractorTest {

    private val locationRepository: LocationRepository = mockk()
    private val locationInteractor: LocationInteractor = LocationInteractorImpl(locationRepository)

    @Test
    fun getEnterPointsTest() {
        //Arrange
        every { locationRepository.getEnterPoints() } returns LOCATION_EXPECTED_RESULT
        val expectedResult = LOCATION_EXPECTED_RESULT

        //Act
        val actualResult = locationInteractor.getEnterPoints()

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
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
        private val LOCATION_EXPECTED_RESULT = listOf(
            LocationModel(
                R.string.main_entrance,
                LocationPoint(43.494545, 43.596298)
            ),

            LocationModel(
                R.string.iopam,
                LocationPoint(43.495200, 43.597663)
            ),

            LocationModel(
                R.string.information_centre,
                LocationPoint(43.496285, 43.595495)
            ),

            LocationModel(
                R.string.fom,
                LocationPoint(43.496721, 43.594947)
            ),

            LocationModel(
                R.string.sports_complex,
                LocationPoint(43.499001, 43.594629)
            ),

            LocationModel(
                R.string.iodams,
                LocationPoint(43.497785, 43.594035)
            ),

            LocationModel(
                R.string.ioacd,
                LocationPoint(43.498807, 43.593489)
            ),

            LocationModel(
                R.string.eatf,
                LocationPoint(43.499304, 43.592977)
            )
        )

        private val MAIN_ENTRANCE_EXPECTED_RESULT = LOCATION_EXPECTED_RESULT[0]
    }
}