package com.snakelord.pets.kbsustudentassistance.navigation.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.data.model.location.LocationPoint
import com.snakelord.pets.kbsustudentassistance.domain.interactor.navigation.LocationInteractor
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.NavigationViewModel
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationViewModelTest {

    private val locationInteractor: LocationInteractor = mockk()
    private lateinit var navigationViewModel: NavigationViewModel
    private val currentLocationObserver: Observer<LocationModel> = mockk()
    private val locationsObserver: Observer<List<LocationModel>> = mockk()

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        every { locationInteractor.getMainEnterPoint() } returns MAIN_ENTRANCE_EXPECTED_RESULT
        every { locationInteractor.getEnterPoints() } returns LOCATION_EXPECTED_RESULT

        navigationViewModel = NavigationViewModel(locationInteractor)
    }

    @Test
    fun initTest() {
        //Assert
        every { currentLocationObserver.onChanged(any()) } just Runs
        every { locationsObserver.onChanged(any()) } just Runs
    }

    @Test
    fun showSelectedEntranceTest() {
        //Arrange
        val expectedResult = EXPECTED_SELECTED_ENTRANCE

        //Act
        navigationViewModel.showSelectedEntrance(expectedResult)

        //Assert
        every { currentLocationObserver.onChanged(MAIN_ENTRANCE_EXPECTED_RESULT) } just Runs
        every { currentLocationObserver.onChanged(any()) } just Runs
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

        private val EXPECTED_SELECTED_ENTRANCE = LocationModel(
            R.string.iopam,
            LocationPoint(43.495200, 43.597663)
        )
    }
}