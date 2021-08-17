package com.snakelord.pets.kbsustudentassistance.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.StudentDao
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.StudentEntity

@Database(entities = [StudentEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}