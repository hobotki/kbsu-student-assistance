package com.snakelord.pets.kbsustudentassistance.di.login.module

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.StudentLoginApi
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.StudentLoginApiImpl
import dagger.Binds
import dagger.Module

@Module
interface LoginApiModule {
    @Binds
    fun bindsStudentApi(studentLoginApiImpl: StudentLoginApiImpl): StudentLoginApi
}