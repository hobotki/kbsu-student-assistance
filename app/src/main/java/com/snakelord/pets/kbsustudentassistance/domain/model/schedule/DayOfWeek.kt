package com.snakelord.pets.kbsustudentassistance.domain.model.schedule

import androidx.annotation.StringRes

data class DayOfWeek(
    val dayOfMonth: Int,
    @StringRes
    val dayOfWeek: Int
)
