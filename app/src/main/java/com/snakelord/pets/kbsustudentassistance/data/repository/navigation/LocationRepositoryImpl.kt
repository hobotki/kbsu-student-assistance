package com.snakelord.pets.kbsustudentassistance.data.repository.navigation

import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.schedule.ScheduleDao
import com.snakelord.pets.kbsustudentassistance.data.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.data.model.location.LocationPoint
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.domain.repository.navigation.LocationRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Реализация интерфейса [LocationRepository]
 *
 * @author Murad Luguev on 26-08-2021
 */
class LocationRepositoryImpl @Inject constructor(
    private val scheduleDao: ScheduleDao
) : LocationRepository {

    private val locations = listOf(
        LocationModel(
            R.string.main_entrance,
            1,
            LocationPoint(43.494545, 43.596298)
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

    override fun getEnterPoints(): Single<List<LocationModel>> {
        return scheduleDao.getSchedule()
                .map { days ->
                    addLecturesToLocations(
                        days.flatMap { it.lectures }
                    )
                }
        }

    override fun getMainEnterPoint(): LocationModel {
        return locations[0]
    }

    private fun addLecturesToLocations(lectures: List<Lecture>): List<LocationModel> {
        val locationsWithLecture = locations
        for (location in locationsWithLecture) {
            location.lectures = lectures.filter { lecture ->
                lecture.instituteId == location.instituteId
            }
        }
        return locationsWithLecture
    }
}