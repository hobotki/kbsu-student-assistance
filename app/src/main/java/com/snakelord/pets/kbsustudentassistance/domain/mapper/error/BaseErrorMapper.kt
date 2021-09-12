package com.snakelord.pets.kbsustudentassistance.domain.mapper.error

import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.exception.BadResponseException
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.model.OperationError
import java.io.InterruptedIOException
import java.lang.IllegalStateException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Маппер ошибок, возникаемых во время получения данных студента
 *
 * @author Murad Luguev on 27-08-2021
 */
class BaseErrorMapper @Inject constructor(): Mapper<Throwable, OperationError> {
    override fun map(input: Throwable): OperationError {
        return when (input) {
            is UnknownHostException -> {
                OperationError(R.string.failed_to_connect_to_server)
            }
            is InterruptedIOException -> {
                OperationError(R.string.connection_timeout)
            }
            is IllegalStateException -> {
                OperationError(R.string.request_illegal_state)
            }
            is BadResponseException ->  {
                matchErrorCode(input)
            }
            else -> {
                OperationError(R.string.something_went_wrong)
            }
        }
    }

    private fun matchErrorCode(badResponseException: BadResponseException): OperationError {
        return when(badResponseException.responseCode) {
            SERVER_INTERNAL_ERROR -> {
                OperationError(R.string.internal_server_error)
            }
            SERVER_UNAVAILABLE -> {
                OperationError(R.string.server_unavailable)
            }
            NOT_FOUND -> {
                OperationError(R.string.requested_info_not_found)
            }
            REQUEST_TIMEOUT -> {
                OperationError(R.string.request_timeout_error)
            }
            else -> {
                OperationError(R.string.unknown_error_code)
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