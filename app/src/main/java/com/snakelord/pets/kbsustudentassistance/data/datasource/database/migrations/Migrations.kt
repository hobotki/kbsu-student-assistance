package com.snakelord.pets.kbsustudentassistance.data.datasource.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.DatabaseConst.StudentTable

/**
 * Объект для хранения миграций базы данных
 *
 * @author Murad Luguev on 01-09-2021
 */
object Migrations {
    /**
     * Миграция для поднятия версии базы данных с версии 1 до версии 2
     *
     * Удалено поле [year], а поля [fullName] и [specialityCode] были переименованы
     */
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE _${StudentTable.TABLE_NAME} (" +
                    "${StudentTable.COLUMN_ID} INTEGER PRIMARY KEY NOT NULL," +
                    "${StudentTable.COLUMN_FULL_NAME} TEXT NOT NULL," +
                    "${StudentTable.COLUMN_SPECIALITY_CODE} TEXT NOT NULL)")
            database.execSQL("INSERT INTO _${StudentTable.TABLE_NAME} (" +
                    "${StudentTable.COLUMN_ID}," +
                    "${StudentTable.COLUMN_FULL_NAME}," +
                    "${StudentTable.COLUMN_SPECIALITY_CODE}) " +
                    "SELECT " +
                    "${StudentTable.COLUMN_ID}," +
                    "fullName," +
                    "specialtyCode FROM ${StudentTable.TABLE_NAME}")
            database.execSQL("DROP TABLE ${StudentTable.TABLE_NAME}")
            database.execSQL("ALTER TABLE _${StudentTable.TABLE_NAME} " +
                    "RENAME TO ${StudentTable.TABLE_NAME}")
        }
    }
}