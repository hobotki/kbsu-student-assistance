package com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class StudentEntity(
    @PrimaryKey
    val id: Int,

    val fullName: String,

    val specialtyCode: String,

    val year: Int
)