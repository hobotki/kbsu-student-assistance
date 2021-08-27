package com.snakelord.pets.kbsustudentassistance.data.datasource.api

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.model.StudentDto

/**
 * Интерфейс для взаимодействия с API
 *
 * @author Murad Luguev on 27-08-2021
 */
interface StudentLoginApi {
    /**
     * Метод для авторизации студента
     *
     * @param secondName фамилия студента
     * @param recordBookNumber номер зачетной книжки студента
     *
     * @return экземпляр [StudentDto]
     */
    fun loginStudent(secondName: String, recordBookNumber: String): StudentDto
}