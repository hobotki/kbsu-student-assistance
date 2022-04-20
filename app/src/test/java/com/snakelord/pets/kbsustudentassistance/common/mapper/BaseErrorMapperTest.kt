package com.snakelord.pets.kbsustudentassistance.common.mapper

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.exception.BadResponseException
import com.snakelord.pets.kbsustudentassistance.domain.mapper.error.BaseErrorMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.error.OperationError
import org.junit.Test
import java.io.InterruptedIOException
import java.lang.IllegalStateException
import java.net.UnknownHostException

class BaseErrorMapperTest {

    private val studentErrorMapper = BaseErrorMapper()

    @Test
    fun mapUnknownHostException() {
        //Arrange
        val expectedResult = UNKNOWN_HOST_EXPECTED_RESULT

        //Act
        val actualResult = studentErrorMapper.map(UnknownHostException())

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun mapInterruptedIOException() {
        //Arrange
        val expectedResult = INTERRUPTED_IO_EXPECTED_RESULT

        //Act
        val actualResult = studentErrorMapper.map(InterruptedIOException())

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun mapIllegalStateException() {
        //Arrange
        val expectedResult = ILLEGAL_STATE_EXPECTED_RESULT

        //Act
        val actualResult = studentErrorMapper.map(IllegalStateException())

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun mapUnknownException() {
        //Arrange
        val expectedResult = UNKNOWN_EXCEPTION_EXPECTED_RESULT

        //Act
        val actualResult = studentErrorMapper.map(Throwable())

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun mapServerInternalError() {
        //Arrange
        val expectedResult = SERVER_INTERNAL_ERROR_EXPECTED_RESULT

        //Act
        val actualResult = studentErrorMapper.map(BadResponseException(SERVER_INTERNAL_ERROR))

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun mapServerUnavailable() {
        //Arrange
        val expectedResult = SERVER_UNAVAILABLE_EXPECTED_RESULT

        //Act
        val actualResult = studentErrorMapper.map(BadResponseException(SERVER_UNAVAILABLE))

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun mapNotFound() {
        //Arrange
        val expectedResult = NOT_FOUND_EXPECTED_RESULT

        //Act
        val actualResult = studentErrorMapper.map(BadResponseException(NOT_FOUND))

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun mapRequestTimeout() {
        //Arrange
        val expectedResult = REQUEST_TIMEOUT_EXPECTED_RESULT

        //Act
        val actualResult = studentErrorMapper.map(BadResponseException(REQUEST_TIMEOUT))

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun mapUnknownErrorCode() {
        //Arrange
        val expectedResult = UNKNOWN_ERROR_CODE_EXPECTED_RESULT

        //Act
        val actualResult = studentErrorMapper.map(BadResponseException(RANDOM_UNKNOWN_ERROR_CODE))

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    companion object {
        private val UNKNOWN_HOST_EXPECTED_RESULT =
            OperationError(R.string.failed_to_connect_to_server)

        private val INTERRUPTED_IO_EXPECTED_RESULT = OperationError(R.string.connection_timeout)

        private val ILLEGAL_STATE_EXPECTED_RESULT = OperationError(R.string.request_illegal_state)

        private val UNKNOWN_EXCEPTION_EXPECTED_RESULT =
            OperationError(R.string.something_went_wrong)

        private const val SERVER_INTERNAL_ERROR = 500
        private const val SERVER_UNAVAILABLE = 503
        private const val NOT_FOUND = 404
        private const val REQUEST_TIMEOUT = 408

        private const val RANDOM_UNKNOWN_ERROR_CODE = 757

        private val SERVER_INTERNAL_ERROR_EXPECTED_RESULT =
            OperationError(R.string.internal_server_error)

        private val SERVER_UNAVAILABLE_EXPECTED_RESULT =
            OperationError(R.string.server_unavailable)

        private val NOT_FOUND_EXPECTED_RESULT =
            OperationError(R.string.unable_to_recognize_user)

        private val REQUEST_TIMEOUT_EXPECTED_RESULT =
            OperationError(R.string.request_timeout_error)
        private val UNKNOWN_ERROR_CODE_EXPECTED_RESULT =
            OperationError(R.string.unknown_error_code)
    }
}
