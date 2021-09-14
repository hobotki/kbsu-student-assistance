package com.snakelord.pets.kbsustudentassistance.domain.interactor.navigation

import androidx.annotation.WorkerThread
import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.domain.repository.navigation.LocationRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Имплементация интерфейса [LocationInteractor]
 *
 * @property locationRepository репозиторий для получения списка локаций
 *
 * @author Murad Luguev on 26-08-2021
 */
class LocationInteractorImpl @Inject constructor(
    private val locationRepository: LocationRepository
) : LocationInteractor {

    @WorkerThread
    override fun getEnterPoints(): Single<List<LocationModel>> {
        return locationRepository.getEnterPoints()
    }

    override fun getMainEnterPoint(): LocationModel {
        return locationRepository.getMainEnterPoint()
    }
}