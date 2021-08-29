package com.snakelord.pets.kbsustudentassistance.login.domain.interactor

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult
import com.snakelord.pets.kbsustudentassistance.domain.interactor.login.LoginInteractor
import com.snakelord.pets.kbsustudentassistance.domain.interactor.login.LoginInteractorImpl
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.student.StudentDtoMapper
import com.snakelord.pets.kbsustudentassistance.domain.repository.login.StudentRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class LoginInteractorTest {

    private val studentRepository: StudentRepository = mockk()
    private val studentMapper: Mapper<StudentDto, StudentEntity> = StudentDtoMapper()
    private val loginInteractor: LoginInteractor =
        LoginInteractorImpl(studentRepository, studentMapper)

    @Test
    fun studentIsNotLoginedTest() {
        //Arrange
        every { studentRepository.isStudentLogined() } returns Maybe.empty()

        //Act
        val testObserver = loginInteractor.isStudentLogined()
            .test()

        //Assert
        testObserver
            .assertComplete()
            .dispose()
    }

    @Test
    fun studentLoginedTest() {
        //Arrange
        every { studentRepository.isStudentLogined() } returns
                Maybe.fromCallable { EXPECTED_STUDENT_ENTITY }

        //Act
        val testObserver = loginInteractor.isStudentLogined()
            .test()

        //Assert
        testObserver
            .assertValue(EXPECTED_STUDENT_ENTITY)
            .assertComplete()
            .dispose()
    }

    @Test
    fun verifySecondNameEmptyTest() {
        //Arrange
        val expectedResult = FIELD_IS_EMPTY

        //Act
        val actualResult = loginInteractor.verifySecondName(EMPTY_FIELD)

        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun verifySecondNameShortTest() {
        //Arrange
        val expectedResult = FIELD_IS_TOO_SHORT

        //Act
        val actualResult = loginInteractor.verifySecondName(SHORT_FIELD)

        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun verifySecondNameIsCorrectTest() {
        //Arrange
        val expectedResult = SUCCESSFUL

        //Act
        val actualResult = loginInteractor.verifySecondName(STUDENT_SECOND_NAME)

        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun verifyRecordBookEmptyTest() {
        //Arrange
        val expectedResult = FIELD_IS_EMPTY

        //Act
        val actualResult = loginInteractor.verifySecondName(EMPTY_FIELD)

        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun verifyRecordBookShortTest() {
        //Arrange
        val expectedResult = FIELD_IS_TOO_SHORT

        //Act
        val actualResult = loginInteractor.verifySecondName(SHORT_FIELD)

        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun verifyRecordBookIsCorrectTest() {
        //Arrange
        val expectedResult = SUCCESSFUL

        //Act
        val actualResult = loginInteractor.verifySecondName(STUDENT_RECORD_BOOK_NUMBER)

        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun loginStudentTest() {
        //Arrange
        every { studentRepository.loginStudent(any(), any()) } returns Single.fromCallable {
            EXPECTED_STUDENT_DTO
        }

        //Act
        val testObserver = loginInteractor
            .loginUser(STUDENT_SECOND_NAME, STUDENT_RECORD_BOOK_NUMBER)
            .test()

        //Assert
        testObserver
            .assertValue(EXPECTED_STUDENT_DTO)
            .dispose()
    }

    @Test
    fun saveStudentTest() {
        //Arrange
        every { studentRepository.saveStudent(any()) } returns Completable.complete()

        //Act
        val testObserver = loginInteractor
            .saveStudentInfo(EXPECTED_STUDENT_DTO)
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
            specialtyCode = "09.03.01",
            year = 3
        )

        private val EXPECTED_STUDENT_DTO = StudentDto(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialtyCode = "09.03.01",
            year = 3
        )

        private val FIELD_IS_TOO_SHORT = VerificationResult.FIELD_IS_TOO_SHORT
        private val FIELD_IS_EMPTY = VerificationResult.FIELD_IS_EMPTY
        private val SUCCESSFUL = VerificationResult.SUCCESSFUL

        private const val EMPTY_FIELD  = ""
        private const val SHORT_FIELD = "tes"

        private const val STUDENT_SECOND_NAME = "Иванов"
        private const val STUDENT_RECORD_BOOK_NUMBER = "1800218"
    }
}