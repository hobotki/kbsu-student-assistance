package com.snakelord.pets.kbsustudentassistance.data.mapper.student

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.domain.json_adapter.login.StudentJsonAdapter
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import javax.inject.Inject

/**
 * Маппер для преобразования ответа с API в [StudentDto]
 *
 * @author Murad Luguev on 27-08-2021
 */
class StudentLoginMapper @Inject constructor(
    private val studentJsonAdapter: StudentJsonAdapter
) : Mapper<String, StudentDto> {
    override fun map(input: String): StudentDto {
        return studentJsonAdapter.fromJson(input)!!
    }
}