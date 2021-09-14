package com.snakelord.pets.kbsustudentassistance.domain.repository.login

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

/**
 * Интерфейс для получения данных студента с удаленного и локлального хранилища
 *
 * @author Murad Luguev on 27-08-2021
 */
interface StudentRepository {

    /**
     * Функция для авторизации студента
     *
     * @param secondName фамилия студента
     * @param recordBookNumber номер зачётной книжки студента
     *
     * @return данные о студенте типа [Single]<[StudentDto]>
     */
    fun loginStudent(secondName: String, recordBookNumber: String): Single<StudentDto>

    /**
     * Функция для сохранения студента в локльной базе данных
     *
     * @param studentEntity экземпляр класса [StudentEntity]
     * для сохранения информации в базе данных
     *
     * @return состояние операции сохранения типа [Completable]
     */
    fun saveStudent(studentEntity: StudentEntity): Completable

    /**
     * Функция для проверки состояния авторизации студента
     *
     * @return состояние авторизации студента типа [Maybe]<[StudentEntity]>
     */
    fun isStudentLogined(): Maybe<StudentEntity>
}