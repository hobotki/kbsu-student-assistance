package com.snakelord.pets.kbsustudentassistance.di.schedule.module

import com.snakelord.pets.kbsustudentassistance.data.datasource.database.Database
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.schedule.ScheduleDao
import dagger.Module
import dagger.Provides

@Module
class ScheduleDaoModule {
    @Provides
    fun provideScheduleDao(database: Database): ScheduleDao {
        return database.scheduleDao()
    }
}