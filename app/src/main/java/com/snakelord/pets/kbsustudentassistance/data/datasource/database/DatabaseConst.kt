package com.snakelord.pets.kbsustudentassistance.data.datasource.database

/**
 * Объект для хранения констант базы данных
 *
 * @author Murad Luguev on 27-08-2021
 */
object DatabaseConst {
    const val DATABASE_NAME = "applicationDatabase"
    const val DATABASE_VERSION = 3

    /**
     * Константы для таблицы, содержащей данные студента
     */
    object StudentTable {
        const val TABLE_NAME = "student"
        const val COLUMN_ID = "id"
        const val COLUMN_FULL_NAME = "full_name"
        const val COLUMN_SPECIALITY_CODE = "speciality_code"
    }

    /**
     * Константы для таблицы, содержащей расписание
     */
    object ScheduleTable {
        const val TABLE_NAME = "schedule"
        const val COLUMN_DAY = "day"
        const val COLUMN_LECTURES = "lectures"
    }
}