package com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.DatabaseConst.StudentTable

/**
 * Модель для сохранения информации о студенте в базу данных
 *
 * @property id идентификатор студента
 * @property fullName ФИО студента
 * @property specialtyCode код специальности студента
 *
 * @author Murad Luguev on 27-08-2021
 */
@Entity(tableName = StudentTable.TABLE_NAME)
data class StudentEntity(
    @PrimaryKey
    @ColumnInfo(name = StudentTable.COLUMN_ID)
    val id: Int,

    @ColumnInfo(name = StudentTable.COLUMN_FULL_NAME)
    val fullName: String,

    @ColumnInfo(name = StudentTable.COLUMN_SPECIALITY_CODE)
    val specialtyCode: String
)