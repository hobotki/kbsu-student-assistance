package com.snakelord.pets.kbsustudentassistance.domain.repository.pass

import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import io.reactivex.rxjava3.core.Single

/**
 * Репозиторий для получения данных студента
 *
 * @author Murad Luguev on 11-09-2021
 */
interface PassRepository {
    /**
     * Функция, которая возвращает данные о студенте для генерации QR-кода
     *
     * @return данные о студенте типа [Single]<[StudentEntity]>
     */
    fun getStudentData(): Single<StudentEntity>
}