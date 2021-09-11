package com.snakelord.pets.kbsustudentassistance.di.pass.module

import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.data.repository.pass.PassRepositoryImpl
import com.snakelord.pets.kbsustudentassistance.domain.interactor.pass.PassInteractor
import com.snakelord.pets.kbsustudentassistance.domain.interactor.pass.PassInteractorImpl
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.pass.StudentDataMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.pass.StudentMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import com.snakelord.pets.kbsustudentassistance.domain.repository.pass.PassRepository
import dagger.Binds
import dagger.Module

@Module
interface PassModule {

    @Binds
    fun bindPassRepository(passRepositoryImpl: PassRepositoryImpl): PassRepository

    @Binds
    fun bindStudentMapper(studentMapper: StudentMapper): Mapper<StudentEntity, Student>

    @Binds
    fun bindsStudentDataMapper(studentDataMapper: StudentDataMapper): Mapper<Student, String>

    @Binds
    fun bindsPassInteractor(passInteractorImpl: PassInteractorImpl): PassInteractor
}