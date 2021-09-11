package com.snakelord.pets.kbsustudentassistance.pass.domain.interactor

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

class PassInteractorTest {

    private val passRepository: PassRepository = mockk()
    private val studentDataMapper = StudentDataMapper()
    private val studentMapper = StudentMapper()
    private val passInteractor =
        PassInteractorImpl(passRepository, studentDataMapper, studentMapper)

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

    companion object {
        private val EXPECTED_STUDENT_ENTITY = StudentEntity(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialtyCode = "09.03.01-3"
        )

        private val EXPECTED_STUDENT = Student(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialtyCode = "09.03.01-3"
        )
    }
}