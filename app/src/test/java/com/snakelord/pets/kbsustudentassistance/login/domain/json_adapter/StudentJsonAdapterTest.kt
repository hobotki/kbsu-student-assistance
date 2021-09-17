package com.snakelord.pets.kbsustudentassistance.login.domain.json_adapter

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.domain.json_adapter.login.StudentJsonAdapter
import com.squareup.moshi.JsonDataException
import org.junit.Test
import java.io.EOFException

class StudentJsonAdapterTest {

    private val studentJsonAdapter = StudentJsonAdapter()

    @Test
    fun fromJsonTest() {
        //Arrange
        val expectedResult = EXPECTED_STUDENT_DTO

        //Act
        val actualResult = studentJsonAdapter.fromJson(STUDENT_JSON)

        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test(expected = JsonDataException::class)
    fun fromJsonEmptyJsonTest() {
        //Act
        val actualResult = studentJsonAdapter.fromJson(EMPTY_RESPONSE)
    }

    @Test(expected = EOFException::class)
    fun fromJsonBadJsonTest() {
        //Act
        val actualResult = studentJsonAdapter.fromJson(BAD_RESPONSE)
    }

    companion object {
        private const val STUDENT_JSON = """
            [
                {
                    "id": 1,
                    "record_book_number": 1800218,
                    "full_name": "Лугуев Мурад Магдиевич",
                    "second_name": "Лугуев",
                    "specialty_code": "09.03.01-3"
                }
            ]
        """

        private const val BAD_RESPONSE = """
            [
                {
                    "id": 1,
                    "record_book_number": 1800218,
                    "full_name": "Лугуев Мурад Магдиевич",
                    "second_name": "Лугуев",
                    "specialty_code": "09.03.01-3"
                }
        """

        private const val EMPTY_RESPONSE = "[]"

        private val EXPECTED_STUDENT_DTO = StudentDto(
            id = 1,
            fullName = "Лугуев Мурад Магдиевич",
            specialityCode = "09.03.01-3"
        )
    }
}