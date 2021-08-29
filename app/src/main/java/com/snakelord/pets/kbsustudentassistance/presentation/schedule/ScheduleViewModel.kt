package com.snakelord.pets.kbsustudentassistance.presentation.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.domain.interactor.schedule.ScheduleInteractor
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Day
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import com.snakelord.pets.kbsustudentassistance.presentation.common.viewmodel.BaseViewModel
import java.util.*

class ScheduleViewModel(
    private val scheduleInteractor: ScheduleInteractor,
    private val schedulersProvider: SchedulersProvider
) : BaseViewModel() {

    private val scheduleMutableLiveData = MutableLiveData<List<Day>>()

    private val currentDayScheduleMutableLiveData = MutableLiveData<List<Lecture>>()
    val schedule: LiveData<List<Lecture>>
        get() = currentDayScheduleMutableLiveData

    init {
        getScheduleFromDb()
    }

    private fun getScheduleFromDb() {
        val getScheduleFromDbDisposable =
            scheduleInteractor.getScheduleFromDatabase()
                .observeOn(schedulersProvider.main())
                .subscribeOn(schedulersProvider.io())
                .subscribe(
                    { scheduleFromDb ->
                        if (scheduleFromDb.isEmpty())
                            loadScheduleFromApi()
                        else {
                            scheduleMutableLiveData.value = scheduleFromDb
                            showTodaySchedule()
                        }
                    },
                    { throw it }
                )
        compositeDisposable.add(getScheduleFromDbDisposable)
    }

    private fun loadScheduleFromApi() {
        val getSchedulerFromApiDisposable =
            scheduleInteractor.getScheduleFromApi()
                .observeOn(schedulersProvider.main())
                .subscribeOn(schedulersProvider.io())
                .subscribe(
                    { scheduleFromApi -> saveSchedule(scheduleFromApi) },
                    { throw  it }
                )
        compositeDisposable.add(getSchedulerFromApiDisposable)
    }

    private fun saveSchedule(scheduleFromApi: List<DayDto>) {
        val saveScheduleDisposable =
            scheduleInteractor.saveSchedule(scheduleFromApi)
                .observeOn(schedulersProvider.main())
                .subscribeOn(schedulersProvider.io())
                .subscribe(
                    { getScheduleFromDb() },
                    { throw  it }
                )
        compositeDisposable.add(saveScheduleDisposable)
    }

    private fun showTodaySchedule() {
        val calendar = Calendar.getInstance()
        when (calendar[Calendar.DAY_OF_WEEK]) {
            Calendar.MONDAY -> {
                currentDayScheduleMutableLiveData.value =
                    scheduleMutableLiveData.value!![0].lectures
            }
            Calendar.TUESDAY -> {
                currentDayScheduleMutableLiveData.value =
                    scheduleMutableLiveData.value!![1].lectures
            }
            Calendar.WEDNESDAY -> {
                currentDayScheduleMutableLiveData.value =
                    scheduleMutableLiveData.value!![2].lectures
            }
            Calendar.THURSDAY -> {
                currentDayScheduleMutableLiveData.value =
                    scheduleMutableLiveData.value!![3].lectures
            }
            Calendar.FRIDAY -> {
                currentDayScheduleMutableLiveData.value =
                    scheduleMutableLiveData.value!![4].lectures
            }
            else -> {
                currentDayScheduleMutableLiveData.value =
                    scheduleMutableLiveData.value!![0].lectures
            }
        }
    }
}