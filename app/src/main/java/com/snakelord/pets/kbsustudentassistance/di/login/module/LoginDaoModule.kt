package com.snakelord.pets.kbsustudentassistance.di.login.module

import com.snakelord.pets.kbsustudentassistance.data.datasource.database.Database
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.StudentDao
import dagger.Module
import dagger.Provides

@Module
class LoginDaoModule {

    @Provides
    fun providesStudentDao(database: Database): StudentDao {
        return database.studentDao()
    }
}