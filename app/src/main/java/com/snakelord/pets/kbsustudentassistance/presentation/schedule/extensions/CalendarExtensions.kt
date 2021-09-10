package com.snakelord.pets.kbsustudentassistance.presentation.schedule.extensions

import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.DayOfWeek
import java.lang.IllegalStateException
import java.util.Calendar
import kotlin.collections.ArrayList

/**
 * Функция-расширение для [Calendar]
 *
 * Собирает рабочую неделю типа [List]<[DayOfWeek]>
 *
 * @return рабочая неделя типа [List]<[DayOfWeek]>
 *
 * @author Murad Luguev on 01-09-2021
 */
fun Calendar.getWeek(): List<DayOfWeek> {

    if (isWeekend()) {
        add(Calendar.WEEK_OF_YEAR, 1)
    }

    set(Calendar.DAY_OF_WEEK, firstDayOfWeek)

    val daysOfWeek = ArrayList<DayOfWeek>(5)

    while (!isWeekend()) {
        daysOfWeek.add(getDayInWeek())
        add(Calendar.DAY_OF_MONTH, 1)
    }

    return daysOfWeek
}

/**
 * Функция-расширение для [Calendar]
 *
 * Преобразует текущий день в модель дня недели [DayOfWeek]
 *
 * @return модель дня недели [DayOfWeek]
 */
fun Calendar.getDayInWeek(): DayOfWeek {
    val dayOfMonth = get(Calendar.DAY_OF_MONTH)
    val dayOfWeek = when (get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> {
            R.string.monday
        }
        Calendar.TUESDAY -> {
            R.string.tuesday
        }
        Calendar.WEDNESDAY -> {
            R.string.wednesday
        }
        Calendar.THURSDAY -> {
            R.string.thursday
        }
        Calendar.FRIDAY -> {
            R.string.friday
        }
        else -> throw IllegalStateException("Unable to find string res")
    }
    return DayOfWeek(
        dayOfMonth,
        dayOfWeek
    )
}

/**
 * Функция-расширение для [Calendar]
 *
 * Определяет является ли выбранный день выходным
 *
 * @return [true] если текущий день является выходным(суббота или воскресенье) и [false] если нет
 *
 * @author Murad Luguev on 01-09-2021
 */
fun Calendar.isWeekend(): Boolean {
    val currentDayOfWeek = get(Calendar.DAY_OF_WEEK)
    return (currentDayOfWeek == Calendar.SATURDAY) ||
            (currentDayOfWeek == Calendar.SUNDAY)
}

private const val MON = 0
private const val TUE = 1
private const val WED = 2
private const val THU = 3
private const val FRI = 4

/**
 * Фукнция-расширение для [Calendar]
 *
 * Возвращает индекс выбранного дня для WeekAdapter
 *
 * @return индекс выбранного дня
 *
 * @author Murad Luguev on 01-09-2021
 */
fun Calendar.today(): Int {
    return when (get(Calendar.DAY_OF_WEEK)) {
        Calendar.TUESDAY -> {
            TUE
        }
        Calendar.WEDNESDAY -> {
            WED
        }
        Calendar.THURSDAY -> {
            THU
        }
        Calendar.FRIDAY -> {
            FRI
        }
        else -> {
            MON
        }
    }
}