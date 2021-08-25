package com.snakelord.pets.kbsustudentassistance.domain.interactor.login

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult.*
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.repository.login.StudentRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoginInteractorImpl @Inject constructor(
    private val studentRepository: StudentRepository,
    private val studentMapper: Mapper<StudentDto, StudentEntity>
): LoginInteractor {

    override fun verifySecondName(secondName: String): VerificationResult {
        return when {
            secondName.isEmpty() -> FIELD_IS_EMPTY
            secondName.length < MINIMAL_SECOND_NAME_LENGTH -> FIELD_IS_TOO_SHORT
            else -> SUCCESSFUL
        }
    }

    override fun verifyRecordBookNumber(recordBookNumber: String): VerificationResult {
        return when {
            recordBookNumber.isEmpty() -> FIELD_IS_EMPTY
            recordBookNumber.length < RECORD_BOOK_NUMBER_LENGTH -> FIELD_IS_TOO_SHORT
            else -> SUCCESSFUL
        }
    }

    override fun loginUser(secondName: String, recordBookNumber: String): Single<StudentDto> {
        return studentRepository.loginStudent(secondName, recordBookNumber)
    }

    override fun saveStudentInfo(studentDto: StudentDto): Completable {
        val studentEntity = studentMapper.map(studentDto)
        return studentRepository.saveStudent(studentEntity)
    }

    override fun isStudentLogined(): Maybe<StudentEntity> {
        return studentRepository.isStudentLogined()
    }

    companion object {
        private const val RECORD_BOOK_NUMBER_LENGTH = 7
        private const val MINIMAL_SECOND_NAME_LENGTH = 4
    }
}