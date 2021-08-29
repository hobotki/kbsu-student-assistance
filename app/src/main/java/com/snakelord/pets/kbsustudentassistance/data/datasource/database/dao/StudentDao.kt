package com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.DatabaseConst
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.StudentEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

/**
 * DAO для работы с таблицей студента
 *
 * @author Murad Luguev on 27-08-2021
 */
@Dao
interface StudentDao {
    /**
     * Функция для сохранения студента в базу данных
     *
     * @param studentEntity информация о студенте
     * @return экземпляр [Completable]
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveStudent(studentEntity: StudentEntity): Completable

    /**
     * Функция для проверки состояния авторизации студента
     *
     * @return экземпляр [Maybe] с типом [StudentEntity]
     */
    @Query("SELECT * FROM ${DatabaseConst.STUDENT_TABLE_NAME}")
    fun isUserLogined(): Maybe<StudentEntity>
}