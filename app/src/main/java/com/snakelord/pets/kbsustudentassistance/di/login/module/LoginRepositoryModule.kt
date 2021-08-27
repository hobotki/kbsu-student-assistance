package com.snakelord.pets.kbsustudentassistance.di.login.module

import com.snakelord.pets.kbsustudentassistance.data.repository.login.StudentRepositoryImpl
import com.snakelord.pets.kbsustudentassistance.domain.repository.login.StudentRepository
import dagger.Binds
import dagger.Module

@Module
interface LoginRepositoryModule {
    @Binds
    fun bindsStudentRepository(studentRepositoryImpl: StudentRepositoryImpl): StudentRepository
}