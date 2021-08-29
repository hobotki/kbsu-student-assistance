package com.snakelord.pets.kbsustudentassistance.di.login.module

import com.snakelord.pets.kbsustudentassistance.data.repository.login.StudentRepositoryImpl
import com.snakelord.pets.kbsustudentassistance.domain.repository.login.StudentRepository
import dagger.Binds
import dagger.Module

/**
 * Dagger-модуль для предоставления [StudentRepository]
 * для работы с данными студента
 *
 * @author Murad Luguev on 27-08-2021
 */
@Module
interface LoginRepositoryModule {
    /**
     * Функция, которая связвывает заданную реализацию с интерфейсом [StudentRepository]
     *
     * @param studentRepositoryImpl реализация интерфейса [StudentRepository]
     *
     * @return экземпляр [StudentRepository]
     */
    @Binds
    fun bindsStudentRepository(studentRepositoryImpl: StudentRepositoryImpl): StudentRepository
}