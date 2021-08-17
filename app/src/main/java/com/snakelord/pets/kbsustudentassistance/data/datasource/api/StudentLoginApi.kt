package com.snakelord.pets.kbsustudentassistance.data.datasource.api

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.model.StudentDto

interface StudentLoginApi {
    fun loginStudent(secondName: String, recordBookNumber: String): StudentDto
}