package com.snakelord.pets.kbsustudentassistance.domain.interactor.pass

import android.graphics.Bitmap
import android.graphics.Color
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import com.snakelord.pets.kbsustudentassistance.domain.repository.pass.PassRepository
import com.snakelord.pets.kbsustudentassistance.utils.BitmapUtil
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Реализация интерфейса [PassInteractor]
 *
 * @property passRepository репозиторий для получения данных студента
 * @property studentDataMapper маппер для преобразования [Student] в JSON
 * @property studentMapper маппер для преобразования [StudentEntity] в [Student]
 *
 * @author Murad Luguev on 11-09-2021
 */
class PassInteractorImpl @Inject constructor(
    private val passRepository: PassRepository,
    private val studentDataMapper: Mapper<Student, String>,
    private val studentMapper: Mapper<StudentEntity, Student>,
) : PassInteractor {

    override fun getStudentData(): Single<Student> {
        return passRepository.getStudentData()
            .map { studentEntity -> studentMapper.map(studentEntity) }
    }

    override fun getQrCode(student: Student, size: Int): Single<Bitmap> {
        return Single.fromCallable {
            val studentData = studentDataMapper.map(student)
            return@fromCallable BitmapUtil.generateQrCodeBitmap(studentData, size)
        }
    }
}