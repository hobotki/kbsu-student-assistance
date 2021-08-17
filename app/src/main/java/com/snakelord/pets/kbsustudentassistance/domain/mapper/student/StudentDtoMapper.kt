package com.snakelord.pets.kbsustudentassistance.domain.mapper.student

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import javax.inject.Inject

class StudentDtoMapper @Inject constructor(): Mapper<StudentDto, StudentEntity> {
    override fun map(input: StudentDto): StudentEntity {
        return StudentEntity(
            id = input.id,
            fullName = input.fullName,
            specialtyCode = input.specialtyCode,
            year = input.year
        )
    }
}