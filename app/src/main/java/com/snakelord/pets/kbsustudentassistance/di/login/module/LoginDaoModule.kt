package com.snakelord.pets.kbsustudentassistance.di.login.module

import com.snakelord.pets.kbsustudentassistance.data.datasource.database.Database
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.StudentDao
import dagger.Module
import dagger.Provides

/**
 * Dagger-модуль для предоставления экземпляра [StudentDao]
 *
 * @author Murad Luguev on 27-08-2021
 */
@Module
class LoginDaoModule {

    /**
     * Предоставляет экземпляр [StudentDao]
     * для работы с таблицей студента в базе данных
     *
     * @param database экземпляр базы данных [Database]
     *
     * @return экземпляр [StudentDao]
     */
    @Provides
    fun providesStudentDao(database: Database): StudentDao {
        return database.studentDao()
    }
}