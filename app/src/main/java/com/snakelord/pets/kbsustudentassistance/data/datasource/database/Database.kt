package com.snakelord.pets.kbsustudentassistance.data.datasource.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.schedule.ScheduleDao
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.student.StudentDao
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule.DayEntity
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity

/**
 * База данных приложения
 *
 * @author Murad Luguev on 27-08-2021
 */
@Database(
    entities = [StudentEntity::class, DayEntity::class],
    version = DatabaseConst.DATABASE_VERSION,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 2, to = 3)
    ]
)
abstract class Database : RoomDatabase() {
    /**
     * Функция для получения DAO для работы со страницей студента
     *
     * @return экземпляр [StudentDao]
     */
    abstract fun studentDao(): StudentDao

    abstract fun scheduleDao(): ScheduleDao
}