package com.snakelord.pets.kbsustudentassistance.pass.domain.mapper

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.mapper.pass.StudentMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import org.junit.Test

class StudentMapperTest {

    private val studentMapper = StudentMapper()

    @Test
    fun mapTest() {
        //Arrange
        val expectedResult = EXPECTED_STUDENT

        //Act
        val actualResult = studentMapper.map(EXPECTED_STUDENT_ENTITY)

        //Assert
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    companion object {
        private val EXPECTED_STUDENT_ENTITY = StudentEntity(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialityCode = "09.03.01-3"
        )

        private val EXPECTED_STUDENT = Student(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialityCode = "09.03.01-3"
        )
    }
}