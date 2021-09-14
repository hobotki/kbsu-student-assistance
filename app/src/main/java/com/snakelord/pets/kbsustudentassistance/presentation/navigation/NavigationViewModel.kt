package com.snakelord.pets.kbsustudentassistance.presentation.navigation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.snakelord.pets.kbsustudentassistance.domain.interactor.navigation.LocationInteractor
import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.presentation.application.KbsuStudentAssistanceApp
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.isPackageExist
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import com.snakelord.pets.kbsustudentassistance.presentation.common.theme.ThemeChanger
import com.yandex.mapkit.MapKitFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * ViewModel для фрагмента навигации
 *
 * @property locationInteractor интерактор для отображения локаций
 * @property schedulersProvider проводник планировщиков для асинхронной работы
 * @property themeChanger утилитный класс для взаимодействия с темой приложения
 * @param application [Application] для работы с контекстом
 *
 * @author Murad Luguev on 26-08-2021
 */
class NavigationViewModel(
    private val locationInteractor: LocationInteractor,
    private val schedulersProvider: SchedulersProvider,
    private val themeChanger: ThemeChanger,
    application: Application,
) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()

    private val currentLocationMutableLiveData = MutableLiveData<LocationModel>()
    val currentLocation: LiveData<LocationModel>
        get() = currentLocationMutableLiveData

    private val locationsMutableLiveData = MutableLiveData<List<LocationModel>>()
    val locations: LiveData<List<LocationModel>>
        get() = locationsMutableLiveData

    var isBottomSheetExpanded = false
        private set

    init {
        MapKitFactory.initialize(getApplication())
        prepareMap()
    }

    private fun prepareMap() {
        currentLocationMutableLiveData.value = locationInteractor.getMainEnterPoint()

        val locationDisposable = locationInteractor.getEnterPoints()
            .observeOn(schedulersProvider.main())
            .subscribeOn(schedulersProvider.io())
            .subscribe { locations ->
                locationsMutableLiveData.value = locations
            }
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

    /**
     * Функция для проверки текущей темы
     *
     * @return * [true] если приложение использует темную тему
     * * [false] если приложение не использует темную тему
     */
    fun isAppInDarkTheme(): Boolean {
        return themeChanger.isAppInDarkTheme()
    }

    /**
     * Функция, которая устанавливает текущее состояние BottomSheet
     *
     * @param isExpanded раскрыт ли BottomSheet
     */
    fun setBottomSheetExpandedState(isExpanded: Boolean) {
        isBottomSheetExpanded = isExpanded
    }

    /**
     * Функция для проверки наличия приложения "Яндекс.Карты" на устройстве
     *
     * @return * [true] если приложение установлено
     * * [false] если приложение не установлено
     */
    fun isYandexMapsInstalled(): Boolean {
        return getApplication<KbsuStudentAssistanceApp>()
            .isPackageExist(YANDEX_MAPS_PACKAGE_NAME)
    }

    /**
     * Функция для проверки наличия приложения "Google Карты" на устройстве
     *
     * @return * [true] если приложение установлено
     * * [false] если приложение не установлено
     */
    fun isGoogleMapsInstalled(): Boolean {
        return getApplication<KbsuStudentAssistanceApp>()
            .isPackageExist(GOOGLE_MAPS_PACKAGE_NAME)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    companion object {
        private const val YANDEX_MAPS_PACKAGE_NAME = "ru.yandex.yandexmaps"
        private const val GOOGLE_MAPS_PACKAGE_NAME = "com.google.android.apps.maps"
    }
}