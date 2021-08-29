package com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.DatabaseConst

/**
 * Модель для сохранения информации о студенте в базу данных
 *
 * @property id идентификатор студента
 * @property fullName ФИО студента
 * @property specialtyCode код специальности студента
 * @property year год обучения студента
 *
 * @author Murad Luguev on 27-08-2021
 */
@Entity(tableName = DatabaseConst.STUDENT_TABLE_NAME)
data class StudentEntity(
    @PrimaryKey
    val id: Int,

    val fullName: String,

    val specialtyCode: String,

    val year: Int
)