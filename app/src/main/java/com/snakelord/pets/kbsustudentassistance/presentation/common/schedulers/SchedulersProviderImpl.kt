package com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

/**
 * Проводник планировщиков для приложения
 *
 * @author Murad Luguev on 27-08-2021
 */
class SchedulersProviderImpl @Inject constructor() : SchedulersProvider {
    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}