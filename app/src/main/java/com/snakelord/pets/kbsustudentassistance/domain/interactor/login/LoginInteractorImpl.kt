package com.snakelord.pets.kbsustudentassistance.domain.interactor.login

import androidx.annotation.WorkerThread
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.model.login.VerificationResult
import com.snakelord.pets.kbsustudentassistance.domain.model.login.VerificationResult.*
import com.snakelord.pets.kbsustudentassistance.domain.extensions.findInvalidSymbols
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.repository.login.StudentRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Реализация интерфейса [LoginInteractor]
 *
 * @property studentRepository репозиторий для получения информации о студенте
 * @property studentMapper маппер для преобразования [StudentDto] в [StudentEntity]
 *
 * @author Murad Luguev on 27-08-2021
 */
class LoginInteractorImpl @Inject constructor(
    private val studentRepository: StudentRepository,
    private val studentMapper: Mapper<StudentDto, StudentEntity>
): LoginInteractor {

    override fun verifySecondName(secondName: String): VerificationResult {
        return verify(
            secondName,
            MINIMAL_SECOND_NAME_LENGTH,
            SECOND_NAME_INVALID_SYMBOLS_PATTERN
        )
    }

    override fun verifyRecordBookNumber(recordBookNumber: String): VerificationResult {
        return verify(
            recordBookNumber,
            RECORD_BOOK_NUMBER_LENGTH,
            RECORD_BOOK_NUMBER_INVALID_SYMBOLS_PATTERN
        )
    }

    private fun verify(field: String, minimalLength: Int, pattern: String): VerificationResult {
        return when {
            field.isEmpty() -> {
                FIELD_IS_EMPTY
            }
            field.length < minimalLength -> {
                FIELD_IS_TOO_SHORT
            }
            field.findInvalidSymbols(pattern) -> {
                FIELD_CONTAINS_INVALID_SYMBOLS
            }
            else -> {
                SUCCESSFUL
            }
        }
    }

    @WorkerThread
    override fun loginStudent(secondName: String, recordBookNumber: String): Single<StudentDto> {
        return studentRepository.loginStudent(secondName, recordBookNumber)
    }

    @WorkerThread
    override fun saveStudentInfo(studentDto: StudentDto): Completable {
        val studentEntity = studentMapper.map(studentDto)
        return studentRepository.saveStudent(studentEntity)
    }

    @WorkerThread
    override fun isStudentLogined(): Maybe<StudentEntity> {
        return studentRepository.isStudentLogined()
    }

    companion object {
        private const val RECORD_BOOK_NUMBER_LENGTH = 7
        private const val MINIMAL_SECOND_NAME_LENGTH = 4
        private const val SECOND_NAME_INVALID_SYMBOLS_PATTERN = "[^а-яА-Яa-zA-Z-]"
        private const val RECORD_BOOK_NUMBER_INVALID_SYMBOLS_PATTERN = "[^0-9]"
    }
}