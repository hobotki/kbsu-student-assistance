package com.snakelord.pets.kbsustudentassistance.domain.mapper.pass

import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import com.squareup.moshi.Moshi
import javax.inject.Inject

class StudentDataMapper @Inject constructor() : Mapper<Student, String> {
    override fun map(input: Student): String {
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Student::class.java)
        return adapter.toJson(input)
    }
}