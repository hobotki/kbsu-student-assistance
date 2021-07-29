package com.snakelord.pets.kbsustudentassistance.domain.interactor

import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult.*

class LoginInteractor {

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

    companion object {
        private const val RECORD_BOOK_NUMBER_LENGTH = 7
        private const val MINIMAL_SECOND_NAME_LENGTH = 4
    }
}