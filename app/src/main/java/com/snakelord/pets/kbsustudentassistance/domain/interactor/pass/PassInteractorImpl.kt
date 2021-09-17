package com.snakelord.pets.kbsustudentassistance.domain.interactor.pass

import androidx.annotation.WorkerThread
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.pass.StudentDataMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import com.snakelord.pets.kbsustudentassistance.domain.repository.pass.PassRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Реализация интерфейса [PassInteractor]
 *
 * @property passRepository репозиторий для получения данных студента
 * @property studentMapper маппер для преобразования [StudentEntity] в [Student]
 * @property studentDataMapper маппер для преобразования [Student] в [String]
 *
 * @author Murad Luguev on 11-09-2021
 */
class PassInteractorImpl @Inject constructor(
    private val passRepository: PassRepository,
    private val studentMapper: Mapper<StudentEntity, Student>,
    private val studentDataMapper: Mapper<Student, String>
) : PassInteractor {

    @WorkerThread
    override fun getStudentData(): Single<Student> {
        return passRepository.getStudentData()
            .map { studentEntity -> studentMapper.map(studentEntity) }
    }

    override fun convertStudentToJson(student: Student): String {
        return studentDataMapper.map(student)
    }
}