package com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule

import androidx.room.*
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.DatabaseConst.ScheduleTable
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.typeconvertor.LecturesTypeConvertor
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture

@Entity(tableName = ScheduleTable.TABLE_NAME)
@TypeConverters(LecturesTypeConvertor::class)
data class DayEntity(
    @PrimaryKey
    @ColumnInfo(name = ScheduleTable.COLUMN_DAY)
    val dayName: String,

    @ColumnInfo(name = ScheduleTable.COLUMN_LECTURES)
    val lectures: List<Lecture>
)