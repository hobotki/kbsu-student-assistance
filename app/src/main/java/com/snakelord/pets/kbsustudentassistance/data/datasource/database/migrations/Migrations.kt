package com.snakelord.pets.kbsustudentassistance.data.datasource.database.migrations

import androidx.room.DeleteColumn
import androidx.room.migration.AutoMigrationSpec
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.DatabaseConst.StudentTable

object Migrations {
    @DeleteColumn(tableName = StudentTable.TABLE_NAME, columnName = StudentTable.COLUMN_YEAR)
    class RemoveYearMigration : AutoMigrationSpec
}