package com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.student

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.DatabaseConst
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

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
    @Query("SELECT * FROM ${DatabaseConst.StudentTable.TABLE_NAME}")
    fun isUserLogined(): Maybe<StudentEntity>

    /**
     * Функция для получения кода специальности студента
     *
     * @return код специальности студента типа [String]
     */
    @Query("SELECT ${DatabaseConst.StudentTable.COLUMN_SPECIALITY_CODE} " +
            "FROM ${DatabaseConst.StudentTable.TABLE_NAME}")
    fun getSpecialityCode(): String

    /**
     * Функция для получения информации о студенте
     *
     * @return данные студента типа [Single]<[StudentEntity]>
     */
    @Query("SELECT * FROM ${DatabaseConst.StudentTable.TABLE_NAME}")
    fun getStudentData(): Single<StudentEntity>
}