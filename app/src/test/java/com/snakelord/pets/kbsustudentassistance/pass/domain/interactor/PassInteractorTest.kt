package com.snakelord.pets.kbsustudentassistance.pass.domain.interactor

import com.google.common.truth.Truth
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.interactor.pass.PassInteractorImpl
import com.snakelord.pets.kbsustudentassistance.domain.mapper.pass.StudentDataMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.pass.StudentMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import com.snakelord.pets.kbsustudentassistance.domain.repository.pass.PassRepository
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class PassInteractorImplTest {

    private val passRepository: PassRepository = mockk()
    private val studentMapper = StudentMapper()
    private val studentDataMapper = StudentDataMapper()
    private val passInteractor =
        PassInteractorImpl(passRepository, studentMapper, studentDataMapper)

    @Test
    fun getStudentDataTest() {
        //Assert
        every { passRepository.getStudentData() } returns Single.just(EXPECTED_STUDENT_ENTITY)
        val expectedResult = EXPECTED_STUDENT

        //Act
        val actualResult = passInteractor.getStudentData()
            .test()

        //Assert
        actualResult
            .assertValue(expectedResult)
            .dispose()
    }

    @Test
    fun convertStudentToTest() {
        //Arrange
        val expectedResult = EXPECTED_JSON

        //Act
        val actualResult = passInteractor.convertStudentToJson(EXPECTED_STUDENT)

        //Assert
        Truth.assertThat(expectedResult).isEqualTo(actualResult)
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

        private val EXPECTED_JSON = """
            {"id":3,"fullName":"Иванов Иван Иванович","specialityCode":"09.03.01-3"}
        """
            .trimIndent()
    }
}