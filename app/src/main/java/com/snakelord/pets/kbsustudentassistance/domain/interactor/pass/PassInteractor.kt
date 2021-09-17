package com.snakelord.pets.kbsustudentassistance.domain.interactor.pass

import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import io.reactivex.rxjava3.core.Single

/**
 * Интерактор для работы с данными студента и генерации QR-кода
 *
 * @author Murad Luguev on 11-09-2021
 */
interface PassInteractor {
    /**
     * Функция, которая возвращает данные студента
     *
     * @return данные студента типа [Single]<[Student]>
     */
    fun getStudentData(): Single<Student>

    /**
     * Преобразует данные студента типа [Student] в JSON
     *
     * @param student данные о студенте
     *
     * @return данные студента представленные в виде JSON
     */
    fun convertStudentToJson(student: Student): String
}