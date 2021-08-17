package com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.StudentEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveStudent(studentEntity: StudentEntity): Completable

    @Query("SELECT * FROM student")
    fun isUserLogined(): Maybe<StudentEntity>
}