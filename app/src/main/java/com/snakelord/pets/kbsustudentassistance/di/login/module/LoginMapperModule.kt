package com.snakelord.pets.kbsustudentassistance.di.login.module

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.StudentEntity
import com.snakelord.pets.kbsustudentassistance.data.mapper.student.StudentMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.error.StudentErrorMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.student.StudentDtoMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.Error
import dagger.Binds
import dagger.Module

@Module
interface LoginMapperModule {
    @Binds
    fun bindsStudentMapper(studentMapper: StudentMapper): Mapper<String, StudentDto>

    @Binds
    fun bindsStudentErrorMapper(studentErrorMapper: StudentErrorMapper): Mapper<Throwable, Error>

    @Binds
    fun bindsStudentDtoMapper(studentDtoMapper: StudentDtoMapper): Mapper<StudentDto, StudentEntity>
}