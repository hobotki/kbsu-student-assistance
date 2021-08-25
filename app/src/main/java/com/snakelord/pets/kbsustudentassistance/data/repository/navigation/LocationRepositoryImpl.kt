package com.snakelord.pets.kbsustudentassistance.data.repository.navigation

import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.data.model.location.LocationPoint
import com.snakelord.pets.kbsustudentassistance.domain.repository.navigation.LocationRepository
import javax.inject.Inject

/**
 * Реализация интерфейса [LocationRepository]
 *
 * @author Murad Luguev on 26-08-2021
 */
class LocationRepositoryImpl @Inject constructor() : LocationRepository {

    private val locations = listOf(
        LocationModel(
            R.string.main_entrance,
            LocationPoint(43.494545, 43.596298)
        ),

        LocationModel(
            R.string.iopam,
            LocationPoint(43.495200, 43.597663)),

        LocationModel(
            R.string.information_centre,
            LocationPoint(43.496285, 43.595495)),

        LocationModel(
            R.string.fom,
            LocationPoint(43.496721, 43.594947)),

        LocationModel(
            R.string.sports_complex,
            LocationPoint(43.499001, 43.594629)),

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

    override fun getEnterPoints(): List<LocationModel> {
        return locations
    }

    override fun getMainEnterPoint(): LocationModel {
        return locations[0]
    }

}