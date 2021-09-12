package com.snakelord.pets.kbsustudentassistance.di.login.module

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.data.mapper.student.StudentLoginMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.error.BaseErrorMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.student.StudentDtoMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.OperationError
import dagger.Binds
import dagger.Module

/**
 * Dagger-модуль для предоставления мапперов,
 * необходимых для работы экрана авторизации
 *
 * @author Murad Luguev on 27-08-2021
 */
@Module
interface LoginMapperModule {
    /**
     * Функция, которая связвывает заданную реализацию с интерфейсом [Mapper]
     *
     * @param studentLoginMapper реализация интерфейса [Mapper]
     *
     * @return экземпляр [Mapper] с параметрами [String] и [StudentDto]
     */
    @Binds
    fun bindsStudentLoginMapper(studentLoginMapper: StudentLoginMapper): Mapper<String, StudentDto>

    /**
     * Функция, которая связвывает заданную реализацию с интерфейсом [Mapper]
     *
     * @param studentDtoMapper реализация интерфейса [Mapper]
     *
     * @return экземпляр [Mapper] с параметрами [StudentDto] и [StudentEntity]
     */
    @Binds
    fun bindsStudentDtoMapper(studentDtoMapper: StudentDtoMapper):
            Mapper<StudentDto, StudentEntity>
}