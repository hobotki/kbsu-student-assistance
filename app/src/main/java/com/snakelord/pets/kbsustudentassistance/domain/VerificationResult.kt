package com.snakelord.pets.kbsustudentassistance.domain

enum class VerificationResult {
    SUCCESSFUL,
    EMPTY_SECOND_NAME,
    EMPTY_RECORD_BOOK_NUMBER,
    SECOND_NAME_TOO_SHORT,
    RECORD_BOOK_NUMBER_TOO_SHORT
}