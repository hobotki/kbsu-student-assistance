package com.snakelord.pets.kbsustudentassistance.domain.mapper.error

import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.exception.BadResponseException
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.model.Error
import java.io.IOException
import java.io.InterruptedIOException
import java.lang.IllegalStateException
import javax.inject.Inject

class StudentErrorMapper @Inject constructor(): Mapper<Throwable, Error> {
    override fun map(input: Throwable): Error {
        return when (input) {
            is IOException -> {
                Error(R.string.failed_to_connect_to_server)
            }
            is IllegalStateException -> {
                Error(R.string.request_illegal_state)
            }
            is InterruptedIOException -> {
                Error(R.string.connection_timeout)
            }
            is BadResponseException ->  {
                matchErrorCode(input)
            }
            else -> {
                Error(R.string.something_went_wrong)
            }
        }
    }

    private fun matchErrorCode(badResponseException: BadResponseException): Error {
        return when(badResponseException.responseCode) {
            SERVER_INTERNAL_ERROR -> {
                Error(R.string.internal_server_error)
            }
            SERVER_UNAVAILABLE -> {
                Error(R.string.server_unavailable)
            }
            NOT_FOUND -> {
                Error(R.string.student_not_found)
            }
            REQUEST_TIMEOUT -> {
                Error(R.string.request_timeout_error)
            }
            else -> {
                Error(R.string.unknown_error_code)
            }
        }
    }

    companion object {
        private const val SERVER_INTERNAL_ERROR = 500
        private const val SERVER_UNAVAILABLE = 503
        private const val NOT_FOUND = 404
        private const val REQUEST_TIMEOUT = 408
    }
}