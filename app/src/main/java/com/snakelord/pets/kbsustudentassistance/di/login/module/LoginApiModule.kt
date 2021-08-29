package com.snakelord.pets.kbsustudentassistance.di.login.module

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.StudentLoginApi
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.StudentLoginApiImpl
import dagger.Binds
import dagger.Module

/**
 * Dagger-модуль для предоставления экземпляра [StudentLoginApi]
 *
 * @author Murad Luguev on 27-08-2021
 */
@Module
interface LoginApiModule {
    /**
     * Функция, которая связвывает заданную реализацию с интерфейсом [StudentLoginApi]
     *
     * @param studentLoginApiImpl реализация интерфейса [StudentLoginApi]
     *
     * @return экземпляр [StudentLoginApi]
     */
    @Binds
    fun bindsStudentApi(studentLoginApiImpl: StudentLoginApiImpl): StudentLoginApi
}