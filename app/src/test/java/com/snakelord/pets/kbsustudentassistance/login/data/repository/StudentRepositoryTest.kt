package com.snakelord.pets.kbsustudentassistance.login.data.repository

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.StudentLoginApi
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.student.StudentDao
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.data.repository.login.StudentRepositoryImpl
import com.snakelord.pets.kbsustudentassistance.domain.repository.login.StudentRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import org.junit.Test

class StudentRepositoryTest {

    private val studentLoginApi: StudentLoginApi = mockk()
    private val studentDao: StudentDao = mockk()
    private val studentRepository: StudentRepository =
        StudentRepositoryImpl(studentLoginApi, studentDao)

    @Test
    fun studentIsNotLoginedTest() {
        //Arrange
        every { studentDao.isUserLogined() } returns Maybe.empty()

        //Act
        val testObserver = studentRepository.isStudentLogined()
            .test()

        //Assert
        testObserver
            .assertComplete()
            .dispose()
    }

    @Test
    fun studentLoginedTest() {
        //Arrange
        every { studentDao.isUserLogined() } returns Maybe.fromCallable { EXPECTED_STUDENT_ENTITY }

        //Act
        val testObserver = studentRepository.isStudentLogined()
            .test()

        //Assert
        testObserver
            .assertValue(EXPECTED_STUDENT_ENTITY)
            .assertComplete()
            .dispose()
    }

    @Test
    fun loginStudentTest() {
        //Arrange
        every { studentLoginApi.loginStudent(any(), any()) } returns EXPECTED_STUDENT_DTO

        //Act
        val testObserver = studentRepository
            .loginStudent(STUDENT_SECOND_NAME, STUDENT_RECORD_BOOK_NUMBER)
            .test()

        //Assert
        testObserver
            .assertValue(EXPECTED_STUDENT_DTO)
            .dispose()
    }

    @Test
    fun saveStudentTest() {
        //Arrange
        every { studentDao.saveStudent(any()) } returns Completable.complete()

        //Act
        val testObserver = studentRepository
            .saveStudent(EXPECTED_STUDENT_ENTITY)
            .test()

        //Assert
        testObserver
            .assertComplete()
            .dispose()
    }

    companion object {
        private val EXPECTED_STUDENT_ENTITY = StudentEntity(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialtyCode = "09.03.01-3"
        )

        private val EXPECTED_STUDENT_DTO = StudentDto(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialtyCode = "09.03.01-3"
        )

        private const val STUDENT_SECOND_NAME = "Иванов"
        private const val STUDENT_RECORD_BOOK_NUMBER = "1800218"
    }
}