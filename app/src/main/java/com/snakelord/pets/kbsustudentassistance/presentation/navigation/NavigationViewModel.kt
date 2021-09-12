package com.snakelord.pets.kbsustudentassistance.presentation.navigation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.domain.interactor.navigation.LocationInteractor
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import com.yandex.mapkit.MapKitFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * ViewModel для фрагмента навигации
 *
 * @property locationInteractor интерактор для отображения локаций
 *
 * @author Murad Luguev on 26-08-2021
 */
class NavigationViewModel(
    private val locationInteractor: LocationInteractor,
    private val schedulersProvider: SchedulersProvider,
    application: Application
) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()

    private val currentLocationMutableLiveData = MutableLiveData<LocationModel>()
    val currentLocation: LiveData<LocationModel>
        get() = currentLocationMutableLiveData

    private val locationsMutableLiveData = MutableLiveData<List<LocationModel>>()
    val locations: LiveData<List<LocationModel>>
        get() = locationsMutableLiveData

    init {
        MapKitFactory.initialize(getApplication())
        prepareMap()
    }

    private fun prepareMap() {
        currentLocationMutableLiveData.value = locationInteractor.getMainEnterPoint()

        val locationDisposable = locationInteractor.getEnterPoints()
            .observeOn(schedulersProvider.main())
            .subscribeOn(schedulersProvider.io())
            .subscribe(
                { locationsMutableLiveData.value = it },
                { throw it }
            )
        compositeDisposable.add(locationDisposable)
    }

    /**
     * Метод для отображения выбранной локации. Необходим для восстановления состояния
     * при изменении конфигурации
     *
     * @param locationModel экземпляр [LocationModel] выбранной локации
     */
    fun showSelectedEntrance(locationModel: LocationModel) {
        currentLocationMutableLiveData.value = locationModel
    }

    /**
     * Метод для отображения локации при переходе из экрана расписания
     *
     * @param instituteId идентификатор института
     */
    fun showLocationById(instituteId: Int) {
        val institute = locationsMutableLiveData.value?.find {
            it.instituteId == instituteId
        }

        if (institute != null) {
            showSelectedEntrance(institute)
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}