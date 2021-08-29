package com.snakelord.pets.kbsustudentassistance.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.StudentDao
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.StudentEntity

/**
 * База данных приложения
 *
 * @author Murad Luguev on 27-08-2021
 */
@Database(entities = [StudentEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    /**
     * Функция для получения DAO для работы со страницей студента
     *
     * @return экземпляр [StudentDao]
     */
    abstract fun studentDao(): StudentDao
}