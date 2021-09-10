package com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule

import androidx.room.*
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.DatabaseConst.ScheduleTable
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.typeconvertor.LecturesTypeConvertor
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture

/**
 * Модель дня недели для сохранения в базе данных
 *
 * @property dayName название дня недели
 * @property lectures лекции, которые проходят в этот день
 *
 * @author Murad Luguev on 01-09-2021
 */
@Entity(tableName = ScheduleTable.TABLE_NAME)
@TypeConverters(LecturesTypeConvertor::class)
data class DayEntity(
    @PrimaryKey
    @ColumnInfo(name = ScheduleTable.COLUMN_DAY)
    val dayName: String,

    @ColumnInfo(name = ScheduleTable.COLUMN_LECTURES)
    val lectures: List<Lecture>
)