package com.snakelord.pets.kbsustudentassistance.domain.model.pass

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Student(
    val id: Int,

    val fullName: String,

    val specialityCode: String,
)