package com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Проводник планировщиков для тестов
 *
 * @author Murad Luguev on 27-08-2021
 */
class SchedulersProviderTest : SchedulersProvider {
    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun main(): Scheduler {
        return Schedulers.trampoline()
    }
}