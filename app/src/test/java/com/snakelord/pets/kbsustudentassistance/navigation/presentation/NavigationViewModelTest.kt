package com.snakelord.pets.kbsustudentassistance.navigation.presentation

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule.DayEntity
import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationPoint
import com.snakelord.pets.kbsustudentassistance.domain.interactor.navigation.LocationInteractor
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProviderTest
import com.snakelord.pets.kbsustudentassistance.presentation.common.theme.ThemeChanger
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.NavigationViewModel
import com.yandex.mapkit.MapKitFactory
import io.mockk.*
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(MapKitFactory::class)
class NavigationViewModelTest {

    private val locationInteractor: LocationInteractor = mockk()
    private val themeChanger: ThemeChanger = mockk()
    private val schedulersProvider: SchedulersProvider = SchedulersProviderTest()
    private lateinit var navigationViewModel: NavigationViewModel
    private val currentLocationObserver: Observer<LocationModel> = mockk()
    private val locationsObserver: Observer<List<LocationModel>> = mockk()

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        every { locationInteractor.getMainEnterPoint() } returns MAIN_ENTRANCE_EXPECTED_RESULT
        every { locationInteractor.getEnterPoints() } returns Single.just(LOCATION_EXPECTED_RESULT)
        every { currentLocationObserver.onChanged(any()) } just Runs
        every { locationsObserver.onChanged(any()) } just Runs

        val application: Application = mockk()

        PowerMockito.mockStatic(MapKitFactory::class.java)
        PowerMockito.doNothing()
            .`when`(
                MapKitFactory::class.java,
                "initialize",
                Mockito.any(Context::class.java)
            )

        navigationViewModel = NavigationViewModel(
            locationInteractor,
            schedulersProvider,
            themeChanger,
            application
        )

        navigationViewModel.currentLocation.observeForever(currentLocationObserver)
        navigationViewModel.locations.observeForever(locationsObserver)
    }

    @Test
    fun initTest() {
        //Assert
        verifySequence {
            currentLocationObserver.onChanged(MAIN_ENTRANCE_EXPECTED_RESULT)
            locationsObserver.onChanged(LOCATION_EXPECTED_RESULT)
        }
    }

    @Test
    fun showSelectedEntranceTest() {
        //Arrange
        val expectedResult = EXPECTED_SELECTED_ENTRANCE

        //Act
        navigationViewModel.showSelectedEntrance(expectedResult)

        //Assert
        verifySequence {
            currentLocationObserver.onChanged(MAIN_ENTRANCE_EXPECTED_RESULT)
            currentLocationObserver.onChanged(EXPECTED_SELECTED_ENTRANCE)
        }
    }

    @Test
    fun showEntranceFromSchedule() {
        //Arrange
        val expectedResult = EXPECTED_SELECTED_ENTRANCE
        val expectedInstituteId = 2

        //Act
        navigationViewModel.showLocationById(expectedInstituteId)

        //Assert
        every { currentLocationObserver.onChanged(MAIN_ENTRANCE_EXPECTED_RESULT) } just Runs
        every { currentLocationObserver.onChanged(expectedResult) } just Runs
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

        private val EXPECTED_SELECTED_ENTRANCE = LocationModel(
            R.string.iopam,
            2,
            LocationPoint(43.495200, 43.597663))
    }
}
