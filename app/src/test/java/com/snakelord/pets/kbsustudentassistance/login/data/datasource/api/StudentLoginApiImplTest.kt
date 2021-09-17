package com.snakelord.pets.kbsustudentassistance.login.data.datasource.api

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.StudentLoginApiImpl
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.exception.BadResponseException
import com.snakelord.pets.kbsustudentassistance.data.mapper.student.StudentLoginMapper
import com.snakelord.pets.kbsustudentassistance.domain.json_adapter.login.StudentJsonAdapter
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import io.mockk.every
import io.mockk.mockk
import okhttp3.OkHttpClient
import okhttp3.Response
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.io.InterruptedIOException

class StudentLoginApiImplTest {

    private val studentMapper: Mapper<String, StudentDto> = StudentLoginMapper(StudentJsonAdapter())
    private val okHttpClient: OkHttpClient = mockk()
    private val response: Response = mockk()
    private val studentLoginApi = StudentLoginApiImpl(okHttpClient, studentMapper)

    @Before
    fun setUp() {
        every { okHttpClient.newCall(any()).execute() } returns response
        every { response.isSuccessful } returns true
    }

    @Test
    fun loginStudentTest() {
        //Arrange
        every { response.body!!.string() } returns RESPONSE_FROM_API
        val expectedResult = EXPECTED_STUDENT_DTO

        //Act
        val actualResult = studentLoginApi
            .loginStudent(STUDENT_SECOND_NAME, STUDENT_RECORD_BOOK_NUMBER)

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test(expected = BadResponseException::class)
    fun loginStudentNotFound() {
        //Arrange
        every { response.body!!.string() } returns EMPTY_RESPONSE

        //Act
        studentLoginApi.loginStudent(STUDENT_WRONG_DATA, STUDENT_WRONG_DATA)
    }

    @Test(expected = IOException::class)
    fun connectionLost() {
        //Arrange
        every { okHttpClient.newCall(any()).execute() } throws IOException()

        //Act
        studentLoginApi.loginStudent(STUDENT_SECOND_NAME, STUDENT_RECORD_BOOK_NUMBER)
    }

    @Test(expected = InterruptedIOException::class)
    fun connectionTimeout() {
        //Arrange
        every { okHttpClient.newCall(any()).execute() } throws InterruptedIOException()

        //Act
        studentLoginApi.loginStudent(STUDENT_SECOND_NAME, STUDENT_RECORD_BOOK_NUMBER)
    }

    @Test(expected = BadResponseException::class)
    fun badResponseTest() {
        //Arrange
        every { response.code } returns RANDOM_ERROR_CODE
        every { response.isSuccessful } returns false

        //Act
        studentLoginApi.loginStudent(STUDENT_SECOND_NAME, STUDENT_RECORD_BOOK_NUMBER)
    }

    companion object {
        private const val RESPONSE_FROM_API = """[
            {
                "id": 3,
                "record_book_number": 1800218,
                "full_name": "Иванов Иван Иванович",
                "second_name": "Иванов",
                "specialty_code": "09.03.01-3"
            }
        ]"""

        private const val EMPTY_RESPONSE = "[]"

        private const val STUDENT_SECOND_NAME = "Иванов"
        private const val STUDENT_RECORD_BOOK_NUMBER = "1800218"

        private const val STUDENT_WRONG_DATA = "test"

        private const val RANDOM_ERROR_CODE = 757

        private val EXPECTED_STUDENT_DTO = StudentDto(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialityCode = "09.03.01-3"
        )
    }
}