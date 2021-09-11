package com.snakelord.pets.kbsustudentassistance.data.repository.pass

import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.student.StudentDao
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.repository.pass.PassRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Реализация интерфейса [PassRepository]
 *
 * @property studentDao DAO для получения данных студента
 *
 * @author Murad Luguev on 11-09-2021
 */
class PassRepositoryImpl @Inject constructor(
    private val studentDao: StudentDao
) : PassRepository {

    override fun getStudentData(): Single<StudentEntity> {
        return studentDao.getStudentData()
    }
}