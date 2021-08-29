package com.snakelord.pets.kbsustudentassistance.data.mapper.student

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.IllegalArgumentException
import javax.inject.Inject

/**
 * Маппер для преобразования ответа с API в [StudentDto]
 *
 * @author Murad Luguev on 27-08-2021
 */
class StudentLoginMapper @Inject constructor() : Mapper<String, StudentDto> {
    override fun map(input: String): StudentDto {
        val moshi = Moshi.Builder().build()
        val responseType = Types.newParameterizedType(List::class.java, StudentDto::class.java)
        val adapter: JsonAdapter<List<StudentDto>> = moshi.adapter(responseType)
        return adapter.fromJson(input)?.first()
            ?: throw IllegalArgumentException("$ILLEGAL_STATE_MESSAGE $input")
    }

    companion object {
        private const val ILLEGAL_STATE_MESSAGE = "Unable to map input"
    }
}