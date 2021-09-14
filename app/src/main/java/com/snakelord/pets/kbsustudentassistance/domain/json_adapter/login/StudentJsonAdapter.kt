package com.snakelord.pets.kbsustudentassistance.domain.json_adapter.login

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import javax.inject.Inject

/**
 * Адаптер для преобразования JSON в [StudentDto]
 *
 * @author Murad Luguev on 15-09-2021
 */
class StudentJsonAdapter @Inject constructor() : JsonAdapter<StudentDto>() {
    override fun fromJson(reader: JsonReader): StudentDto {
        reader.beginArray()
        reader.beginObject()

        reader.skipName()
        val id = reader.nextInt()

        reader.skipName()
        reader.skipValue()

        reader.skipName()
        val fullName = reader.nextString()

        reader.skipName()
        reader.skipValue()

        reader.skipName()
        val specialityCode = reader.nextString()

        reader.endObject()
        reader.endArray()

        return StudentDto(
            fullName,
            id,
            specialityCode
        )
    }

    override fun toJson(writer: JsonWriter, value: StudentDto?) {
        ////Метод не переопределён, т.к. нет необходимости переводить StudentDto в JSON
    }
}