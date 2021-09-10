package com.snakelord.pets.kbsustudentassistance.pass.data.repository

import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.student.StudentDao
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.data.repository.pass.PassRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class PassRepositoryTest {

    private val studentDao: StudentDao = mockk()
    private val passRepositoryImpl = PassRepositoryImpl(studentDao)

    @Test
    fun getStudentDataTest() {
        //Arrange
        every { studentDao.getStudentData() } returns Single.just(EXPECTED_STUDENT_ENTITY)
        val expectedResult = EXPECTED_STUDENT_ENTITY

        //Act
        val actualResult = passRepositoryImpl.getStudentData()
            .test()

        //Assert
        actualResult
            .assertValue(expectedResult)
            .dispose()
    }

    companion object {
        private val EXPECTED_STUDENT_ENTITY = StudentEntity(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialtyCode = "09.03.01-3"
        )
    }
}