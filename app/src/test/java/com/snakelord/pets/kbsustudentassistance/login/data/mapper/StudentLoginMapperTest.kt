package com.snakelord.pets.kbsustudentassistance.login.data.mapper

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.mapper.student.StudentLoginMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.squareup.moshi.JsonEncodingException
import org.junit.Test
import java.io.EOFException

class StudentLoginMapperTest {

    private val studentLoginMapper: Mapper<String, StudentDto> = StudentLoginMapper()

    @Test
    fun mapTest() {
        //Arrange
        val expectedResult = EXPECTED_STUDENT_DTO

        //Act
        val actualResult = studentLoginMapper.map(RESPONSE_FROM_API)

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test(expected = EOFException::class)
    fun mapEmptyStringTest() {
        //Arrange
        val expectedApiResponse = EMPTY_RESPONSE

        //Act
        studentLoginMapper.map(expectedApiResponse)
    }

    @Test(expected = JsonEncodingException::class)
    fun mapBadJson() {
        //Arrange
        val expectedBadResponse = BAD_RESPONSE

        //Act
        studentLoginMapper.map(expectedBadResponse)
    }

    companion object {
        private const val RESPONSE_FROM_API = """[
            {
                "id": 3,
                "record_book_number": 1800218,
                "full_name": "Иванов Иван Иванович",
                "second_name": "Иванов",
                "specialty_code": "09.03.01",
                "year": 3
            }
        ]"""

        private const val BAD_RESPONSE = """[
            {
                "id": 3,
                "record_book_number": 1800218,
                "full_name": "Иванов Иван Иванович",
                "second_name": "Иванов",
                "specialty_code": "09.03.01",
                "year": 3
            ]
        """


        private const val EMPTY_RESPONSE = ""

        private val EXPECTED_STUDENT_DTO = StudentDto(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialtyCode = "09.03.01",
            year = 3
        )
    }
}