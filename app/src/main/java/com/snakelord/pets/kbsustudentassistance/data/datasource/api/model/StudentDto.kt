package com.snakelord.pets.kbsustudentassistance.data.datasource.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StudentDto(
    @field:Json(name = FULL_NAME_JSON_KEY)
    val fullName: String,
    val id: Int,
    @field:Json(name = SPECIALITY_CODE_JSON_KEY)
    val specialtyCode: String,
    val year: Int
) {
    companion object {
        private const val FULL_NAME_JSON_KEY = "full_name"
        private const val SPECIALITY_CODE_JSON_KEY = "specialty_code"
    }
}