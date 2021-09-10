package com.snakelord.pets.kbsustudentassistance.di.schedule.module

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.ScheduleApi
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.ScheduleApiImpl
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule.DayEntity
import com.snakelord.pets.kbsustudentassistance.data.mapper.schedule.ScheduleResponseMapper
import com.snakelord.pets.kbsustudentassistance.data.repository.schedule.ScheduleRepositoryImpl
import com.snakelord.pets.kbsustudentassistance.domain.interactor.schedule.ScheduleInteractor
import com.snakelord.pets.kbsustudentassistance.domain.interactor.schedule.ScheduleInteractorImpl
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.schedule.DaysDtoMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.schedule.DaysEntityMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.schedule.LecturesDtoMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Day
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.domain.repository.schedule.ScheduleRepository
import dagger.Binds
import dagger.Module

/**
 * Модуль для предоставления зависимостей экрану расписания
 *
 * @author Murad Luguev on 01-09-2021
 */
@Module
interface ScheduleModule {

    /**
     * Связывает реализацию [ScheduleApiImpl] с интерфейсом [ScheduleApi]
     *
     * @param scheduleApiImpl реализация интерфейса [ScheduleApi]
     *
     * @return реализация приведенная к интерфейсу [ScheduleApi]
     */
    @Binds
    fun bindScheduleApi(scheduleApiImpl: ScheduleApiImpl): ScheduleApi

    /**
     * Связывает реализацию [ScheduleResponseMapper]
     * с интерфейсом [Mapper]<[String], [List]<[DayDto]>>
     *
     * @param scheduleResponseMapper реализация интерфейса [Mapper]<[String], [List]<[DayDto]>>
     *
     * @return реализация приведенная к интерфейсу [Mapper]<[String], [List]<[DayDto]>>
     */
    @Binds
    fun bindsScheduleResponseMapper(scheduleResponseMapper: ScheduleResponseMapper):
            Mapper<String, List<DayDto>>

    /**
     * Связывает реализацию [LecturesDtoMapper]
     *
     * с интерфейсом [Mapper]<[List]<[LectureDto]>, [List]<[Lecture]>>
     *
     * @param lecturesDtoMapper реализация интерфейса
     * [Mapper]<[List]<[LectureDto]>, [List]<[Lecture]>>
     *
     * @return реализация приведенная к интерфейсу [Mapper]<[List]<[LectureDto]>, [List]<[Lecture]>>
     */
    @Binds
    fun bindsLecturesMapper(lecturesDtoMapper: LecturesDtoMapper):
            Mapper<List<LectureDto>, List<Lecture>>

    /**
     * Связывает реализацию [DaysDtoMapper]
     * с интерфейсом [Mapper]<[List]<[DayDto]>, [List]<[DayEntity]>>
     *
     * @param daysDtoMapper реализация интерфейса
     * [Mapper]<[List]<[DayDto]>, [List]<[DayEntity]>>
     *
     * @return реализация приведенная к интерфейсу [Mapper]<[List]<[DayDto]>, [List]<[DayEntity]>>
     */
    @Binds
    fun bindsDaysDtoMapper(daysDtoMapper: DaysDtoMapper):
            Mapper<List<DayDto>, List<DayEntity>>

    /**
     * Связывает реализацию [DaysEntityMapper]
     * с интерфейсом [Mapper]<[List]<[DayDto]>, [List]<[DayEntity]>>
     *
     * @param daysEntityMapper реализация интерфейса
     * [Mapper]<[List]<[DayEntity]>, [List]<[Day]>>
     *
     * @return реализация приведенная к интерфейсу [Mapper]<[List]<[DayEntity]>, [List]<[Day]>>
     */
    @Binds
    fun bindsDaysEntityMapper(daysEntityMapper: DaysEntityMapper):
            Mapper<List<DayEntity>, List<Day>>

    /**
     * Связывает реализацию [ScheduleRepositoryImpl] с интерфейсом [ScheduleRepository]
     *
     * @param scheduleRepositoryImpl реализация интерфейса [ScheduleApi]
     *
     * @return реализация приведенная к интерфейсу [ScheduleRepository]
     */
    @Binds
    fun bindsScheduleRepository(scheduleRepositoryImpl: ScheduleRepositoryImpl):
            ScheduleRepository

    /**
     * Связывает реализацию [ScheduleInteractorImpl] с интерфейсом [ScheduleInteractor]
     *
     * @param scheduleInteractorImpl реализация интерфейса [ScheduleInteractor]
     *
     * @return реализация приведенная к интерфейсу [ScheduleInteractor]
     */
    @Binds
    fun bindsScheduleInteractor(scheduleInteractorImpl: ScheduleInteractorImpl):
            ScheduleInteractor

}