package com.snakelord.pets.kbsustudentassistance.data.datasource.database.typeconvertor

import androidx.room.TypeConverter
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class LecturesTypeConvertor {
    @TypeConverter
    fun lecturesToJson(lectures: List<Lecture>): String {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Lecture::class.java)
        val adapter: JsonAdapter<List<Lecture>> = moshi.adapter(type)
        return adapter.toJson(lectures)
    }

    @TypeConverter
    fun lecturesFromJson(json: String): List<Lecture>? {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, Lecture::class.java)
        val adapter: JsonAdapter<List<Lecture>> = moshi.adapter(type)
        return adapter.fromJson(json)!!
    }
}