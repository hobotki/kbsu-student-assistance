package com.snakelord.pets.kbsustudentassistance.domain.mapper.pass

import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import javax.inject.Inject

class StudentMapper @Inject constructor() : Mapper<StudentEntity, Student> {
    override fun map(input: StudentEntity): Student {
        return Student(
            id = input.id,
            fullName = input.fullName,
            specialityCode = input.specialityCode
        )
    }
}