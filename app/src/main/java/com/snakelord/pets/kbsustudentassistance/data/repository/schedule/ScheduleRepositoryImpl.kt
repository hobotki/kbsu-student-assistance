package com.snakelord.pets.kbsustudentassistance.data.repository.schedule

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.ScheduleApi
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.schedule.ScheduleDao
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.student.StudentDao
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.schedule.DayEntity
import com.snakelord.pets.kbsustudentassistance.domain.repository.schedule.ScheduleRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Реализация интерфейса [ScheduleRepository]
 *
 * @property scheduleApi API с которого приходит инофрмация
 * @property studentDao DAO для получения кода специальности студента
 * @property scheduleDao DAO для работы с таблицей расписания
 *
 * @author Murad Luguev on 01-09-2021
 */
class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleApi: ScheduleApi,
    private val studentDao: StudentDao,
    private val scheduleDao: ScheduleDao
) : ScheduleRepository {

    override fun getScheduleFromApi(): Single<List<DayDto>> {
        return Single.fromCallable {
            val specialityCode = studentDao.getSpecialityCode()
            return@fromCallable scheduleApi.getSchedule(specialityCode)
        }
    }

    override fun getScheduleFromDatabase(): Single<List<DayEntity>> {
        return scheduleDao.getSchedule()
    }

    override fun saveSchedule(schedule: List<DayEntity>): Completable {
        return scheduleDao.insertSchedule(schedule)
    }

}