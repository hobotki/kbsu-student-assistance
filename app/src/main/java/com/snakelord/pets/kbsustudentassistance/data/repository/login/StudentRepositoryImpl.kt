package com.snakelord.pets.kbsustudentassistance.data.repository.login

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.StudentLoginApi
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.student.StudentDao
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.repository.login.StudentRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Реализация интерфейса [StudentRepository]
 *
 * @property studentLoginApi интерфейс для работы с API
 * @property studentDao DAO для работы с таблицей студента в базе данных
 *
 * @author Murad Luguev on 27-08-2021
 */
class StudentRepositoryImpl @Inject constructor(
    private val studentLoginApi: StudentLoginApi,
    private val studentDao: StudentDao
) : StudentRepository {

    override fun loginStudent(secondName: String, recordBookNumber: String): Single<StudentDto> {
        return Single.fromCallable {
            studentLoginApi.loginStudent(secondName, recordBookNumber)
        }
    }

    override fun saveStudent(studentEntity: StudentEntity): Completable {
        return studentDao.saveStudent(studentEntity)
    }

    override fun isStudentLogined(): Maybe<StudentEntity> {
        return studentDao.isStudentLogined()
    }

}