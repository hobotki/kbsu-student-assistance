package com.snakelord.pets.kbsustudentassistance.domain.interactor

import com.snakelord.pets.kbsustudentassistance.data.model.State
import com.snakelord.pets.kbsustudentassistance.data.model.Student
import com.snakelord.pets.kbsustudentassistance.data.repositiry.StudentRepository
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class LoginInteractor {

    private val repository = StudentRepository()

    fun verifySecondName(secondName: String): VerificationResult {
        return when {
            secondName.isEmpty() -> FIELD_IS_EMPTY
            secondName.length < MINIMAL_SECOND_NAME_LENGTH -> FIELD_IS_TOO_SHORT
            else -> SUCCESSFUL
        }
    }

    fun verifyRecordBookNumber(recordBookNumber: String): VerificationResult {
        return when {
            recordBookNumber.isEmpty() -> FIELD_IS_EMPTY
            recordBookNumber.length < RECORD_BOOK_NUMBER_LENGTH -> FIELD_IS_TOO_SHORT
            else -> SUCCESSFUL
        }
    }

    fun loginUser(secondName: String, recordBookNumber: String): Observable<State> {
        return repository.getStudent(secondName, recordBookNumber)
    }

    companion object {
        private const val RECORD_BOOK_NUMBER_LENGTH = 7
        private const val MINIMAL_SECOND_NAME_LENGTH = 4
    }
}