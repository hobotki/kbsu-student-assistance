package com.snakelord.pets.kbsustudentassistance.domain.interactor.login

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface LoginInteractor {
    fun verifySecondName(secondName: String): VerificationResult
    fun verifyRecordBookNumber(recordBookNumber: String): VerificationResult
    fun loginUser(secondName: String, recordBookNumber: String): Single<StudentDto>
    fun saveStudentInfo(studentDto: StudentDto): Completable
    fun isStudentLogined(): Maybe<StudentEntity>
}