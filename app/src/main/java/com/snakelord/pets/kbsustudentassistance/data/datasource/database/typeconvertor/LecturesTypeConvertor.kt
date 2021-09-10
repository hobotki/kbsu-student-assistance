package com.snakelord.pets.kbsustudentassistance.data.datasource.database.typeconvertor

import androidx.room.TypeConverter
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * TypeConverter для сохранения списка лекций в базе данных
 *
 * @author Murad Luguev on 01-09-2021
 */
class LecturesTypeConvertor {
    /**
     * Функция для перевода списка лекций в JSON для сохранения в базу данных
     *
     * @param lectures список лекций
     * @return список лекций, представленный в формате JSON
     */
    @TypeConverter
    fun lecturesToJson(lectures: List<Lecture>): String {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Lecture::class.java)
        val adapter: JsonAdapter<List<Lecture>> = moshi.adapter(type)
        return adapter.toJson(lectures)
    }

    /**
     * Функция для восстановления списка лекций из JSON
     *
     * @param json список лекций в формате JSON
     * @return список лекций в виде [List]<[Lecture]>
     */
    @TypeConverter
    fun lecturesFromJson(json: String): List<Lecture> {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Lecture::class.java)
        val adapter: JsonAdapter<List<Lecture>> = moshi.adapter(type)
        return adapter.fromJson(json)!!
    }
}