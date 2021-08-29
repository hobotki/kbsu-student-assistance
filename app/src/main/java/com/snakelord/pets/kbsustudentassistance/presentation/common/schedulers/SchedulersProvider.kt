package com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers

import io.reactivex.rxjava3.core.Scheduler

/**
 * Провайдер планировщиков для асинхронного выполнения операций
 *
 * @author Murad Luguev on 27-08-2021
 */
interface SchedulersProvider {
    /**
     * Функция для получения планировщика, работающего на [IO] потоке
     *
     * @return экземпляр [Scheduler] для работы на [IO] потоке
     */
    fun io(): Scheduler

    /**
     * Функция для получения планировщика, работаюшего в [MAIN] потоке
     *
     * @return экземпляр [Scheduler] для работы в [MAIN] потоке
     */
    fun main(): Scheduler
}