package com.snakelord.pets.kbsustudentassistance.domain.mapper.student

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import javax.inject.Inject

/**
 * Маппер для преобразования [StudentDto] в [StudentEntity]
 *
 * @author Murad Luguev on 27-08-2021
 */
class StudentDtoMapper @Inject constructor(): Mapper<StudentDto, StudentEntity> {
    override fun map(input: StudentDto): StudentEntity {
        return StudentEntity(
            id = input.id,
            fullName = input.fullName,
            specialityCode = input.specialityCode
        )
    }
}