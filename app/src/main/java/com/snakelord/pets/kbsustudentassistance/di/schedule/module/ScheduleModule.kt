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

@Module
interface ScheduleModule {

    @Binds
    fun bindScheduleApi(scheduleApiImpl: ScheduleApiImpl): ScheduleApi

    @Binds
    fun bindsScheduleResponseMapper(scheduleResponseMapper: ScheduleResponseMapper):
            Mapper<String, List<DayDto>>

    @Binds
    fun bindsLecturesMapper(lecturesDtoMapper: LecturesDtoMapper):
            Mapper<List<LectureDto>, List<Lecture>>

    @Binds
    fun bindsDaysDtoMapper(daysDtoMapper: DaysDtoMapper):
            Mapper<List<DayDto>, List<DayEntity>>

    @Binds
    fun bindsDaysEntityMapper(daysEntityMapper: DaysEntityMapper):
            Mapper<List<DayEntity>, List<Day>>

    @Binds
    fun bindsScheduleRepository(scheduleRepositoryImpl: ScheduleRepositoryImpl):
            ScheduleRepository

    @Binds
    fun bindsScheduleInteractor(scheduleInteractorImpl: ScheduleInteractorImpl):
            ScheduleInteractor

}