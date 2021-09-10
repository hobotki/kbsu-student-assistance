package com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.schedule

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.DatabaseConst.ScheduleTable
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule.DayEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

/**
 * DAO для получения расписания
 *
 * @author Murad Luguev on 27-08-2021
 */
@Dao
interface ScheduleDao {
    /**
     * Функция для сохранения расписания в базу данных
     *
     * @param schedule расписание типа [List]<[DayEntity]>
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchedule(schedule: List<DayEntity>): Completable

    /**
     * Функция для получения расписания из базы данных
     *
     * @return расписание типа [Single]<[List]<[DayEntity]>>
     */
    @Query("SELECT * FROM ${ScheduleTable.TABLE_NAME}")
    fun getSchedule(): Single<List<DayEntity>>
}