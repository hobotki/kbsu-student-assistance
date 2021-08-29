package com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Модель для получения данных с API
 *
 * @property fullName ФИО студента
 * @property id идентификатор студента
 * @property specialtyCode код специальности студента
 *
 * @author Murad Luguev on 27-08-2021
 */
@JsonClass(generateAdapter = true)
data class StudentDto(
    @field:Json(name = FULL_NAME_JSON_KEY)
    val fullName: String,
    val id: Int,
    @field:Json(name = SPECIALITY_CODE_JSON_KEY)
    val specialtyCode: String
) {
    companion object {
        private const val FULL_NAME_JSON_KEY = "full_name"
        private const val SPECIALITY_CODE_JSON_KEY = "specialty_code"
    }
}