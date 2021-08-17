package com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers

import io.reactivex.rxjava3.core.Scheduler

interface SchedulersProvider {
    fun io(): Scheduler
    fun main(): Scheduler
}