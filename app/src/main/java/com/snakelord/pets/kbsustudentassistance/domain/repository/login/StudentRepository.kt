package com.snakelord.pets.kbsustudentassistance.domain.repository.login

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.StudentEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface StudentRepository {
    fun loginStudent(secondName: String, recordBookNumber: String): Single<StudentDto>
    fun saveStudent(studentEntity: StudentEntity): Completable
    fun isStudentLogined(): Maybe<StudentEntity>
}