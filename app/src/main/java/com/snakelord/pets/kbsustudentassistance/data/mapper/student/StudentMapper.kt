package com.snakelord.pets.kbsustudentassistance.data.mapper.student

import com.snakelord.pets.kbsustudentassistance.common.extensions.toJsonObject
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.squareup.moshi.Moshi
import javax.inject.Inject

class StudentMapper @Inject constructor() : Mapper<String, StudentDto> {
    override fun map(input: String): StudentDto {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(StudentDto::class.java)
        return adapter.fromJson(input.toJsonObject())!!
    }
}