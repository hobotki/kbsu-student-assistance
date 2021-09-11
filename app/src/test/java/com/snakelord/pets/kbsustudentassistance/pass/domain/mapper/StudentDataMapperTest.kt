package com.snakelord.pets.kbsustudentassistance.pass.domain.mapper

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.domain.mapper.pass.StudentDataMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import org.junit.Test

class StudentDataMapperTest {

    private val studentDataMapper = StudentDataMapper()

    @Test
    fun mapTest() {
        //Arrange
        val expectedResult = EXPECTED_RESULT

        //Act
        val actualResult = studentDataMapper.map(EXPECTED_STUDENT)

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    companion object {
        private val EXPECTED_STUDENT = Student(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialtyCode = "09.03.01-3"
        )

        private const val EXPECTED_RESULT =
            """{"id":3,"fullName":"Иванов Иван Иванович","specialtyCode":"09.03.01-3"}"""
    }
}