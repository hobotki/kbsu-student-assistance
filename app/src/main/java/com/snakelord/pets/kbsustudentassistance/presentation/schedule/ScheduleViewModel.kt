package com.snakelord.pets.kbsustudentassistance.presentation.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.domain.interactor.schedule.ScheduleInteractor
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.model.OperationError
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Day
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import com.snakelord.pets.kbsustudentassistance.presentation.common.state.UIStates
import com.snakelord.pets.kbsustudentassistance.presentation.common.viewmodel.BaseViewModel
import com.snakelord.pets.kbsustudentassistance.presentation.schedule.extensions.today
import java.util.*

/**
 * ViewModel для работы с расписанием
 *
 * @property scheduleInteractor интерактор для получения расписания
 * @property schedulersProvider провайдер планировщиков
 *
 * @author Murad Luguev on 01-09-2021
 */
class ScheduleViewModel(
    private val scheduleInteractor: ScheduleInteractor,
    private val schedulersProvider: SchedulersProvider,
    errorMapper: Mapper<Throwable, OperationError>,
) : BaseViewModel(errorMapper) {

    private val scheduleMutableLiveData = MutableLiveData<List<Day>>()

    private val selectedScheduleMutableLiveData = MutableLiveData<List<Lecture>>()
    val selectedSchedule: LiveData<List<Lecture>>
        get() = selectedScheduleMutableLiveData

    init {
        getScheduleFromDb()
    }

    var selectedIndex: Int = Calendar.getInstance().today()
        private set

    private fun getScheduleFromDb() {
        updateUIState(UIStates.Loading)
        val getScheduleFromDbDisposable =
            scheduleInteractor.getScheduleFromDatabase()
                .observeOn(schedulersProvider.main())
                .subscribeOn(schedulersProvider.io())
                .subscribe { scheduleFromDb ->
                    if (scheduleFromDb.isEmpty()) {
                        loadScheduleFromApi()
                    } else {
                        setSchedule(scheduleFromDb)
                    }
                }
        compositeDisposable.add(getScheduleFromDbDisposable)
    }

    fun loadScheduleFromApi() {
        val getSchedulerFromApiDisposable =
            scheduleInteractor.getScheduleFromApi()
                .observeOn(schedulersProvider.main())
                .subscribeOn(schedulersProvider.io())
                .subscribe(
                    { scheduleFromApi -> saveSchedule(scheduleFromApi) },
                    { throwable -> performError(throwable) }
                )
        compositeDisposable.add(getSchedulerFromApiDisposable)
    }

    private fun saveSchedule(scheduleFromApi: List<DayDto>) {
        val saveScheduleDisposable =
            scheduleInteractor.saveSchedule(scheduleFromApi)
                .observeOn(schedulersProvider.main())
                .subscribeOn(schedulersProvider.io())
                .subscribe{ getScheduleFromDb() }
        compositeDisposable.add(saveScheduleDisposable)
    }

    private fun setSchedule(scheduleFromDb: List<Day>) {
        scheduleMutableLiveData.value = scheduleFromDb
        showScheduleByDay()
        updateUIState(UIStates.Successful)
    }

    /**
     * Функция для отображения расписания исходя из выбранного дня,
     * по-умолчанию [Calendar.today]
     *
     * @param index индекс выбранного дня
     */
    fun showScheduleByDay(index: Int = selectedIndex) {
        selectedIndex = index
        selectedScheduleMutableLiveData.value = scheduleMutableLiveData.value!![index].lectures
    }
}