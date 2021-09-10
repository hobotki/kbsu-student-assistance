package com.snakelord.pets.kbsustudentassistance.domain.json_adapter

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import javax.inject.Inject

/**
 * Адаптер для преобразования JSON в список дней недели
 *
 * @author Murad Luguev on 01-09-2021
 */
class ScheduleJsonAdapter @Inject constructor() : JsonAdapter<List<DayDto>>() {

    override fun fromJson(reader: JsonReader): List<DayDto> {
        reader.beginArray()
        reader.beginObject()
        reader.skipName()
        reader.beginArray()

        val days = ArrayList<DayDto>()
        while (reader.hasNext()) {
            days.add(mapDay(reader))
        }

        reader.endArray()
        reader.endObject()
        reader.endArray()

        return days
    }

    private fun mapDay(reader: JsonReader): DayDto {
        reader.beginObject()
        reader.skipName()

        val dayName = reader.nextString()
        reader.skipName()
        reader.beginArray()

        val lectures = ArrayList<LectureDto>()
        while (reader.hasNext()) {
            lectures.add(mapLecture(reader))
        }

        reader.endArray()
        reader.endObject()

        return DayDto(dayName, lectures)
    }

    private fun mapLecture(reader: JsonReader): LectureDto {
        reader.beginObject()

        reader.skipName()
        val lectureName = reader.nextString()

        reader.skipName()
        val teacher = reader.nextString()

        reader.skipName()
        val startTime = reader.nextString()

        reader.skipName()
        val endTime = reader.nextString()

        reader.skipName()
        val classRoom = reader.nextString()

        reader.skipName()
        val instituteId = reader.nextInt()

        reader.endObject()

        return LectureDto(
            lectureName,
            teacher,
            startTime,
            endTime,
            classRoom,
            instituteId
        )
    }

    override fun toJson(writer: JsonWriter, value: List<DayDto>?) {
        //Метод не переопределён, т.к. нет необходимости переводить в json List<DayDto>
    }
}