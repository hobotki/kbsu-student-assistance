package com.snakelord.pets.kbsustudentassistance.di.schedule.component

import com.snakelord.pets.kbsustudentassistance.di.common.component.ApplicationComponent
import com.snakelord.pets.kbsustudentassistance.di.schedule.module.ScheduleModule
import com.snakelord.pets.kbsustudentassistance.di.schedule.scope.ScheduleScope
import com.snakelord.pets.kbsustudentassistance.domain.interactor.schedule.ScheduleInteractor
import dagger.Component

/**
 * Компонент для предоставления зависимостей экрану расписания
 *
 * @author Murad Luguev on 01-09-2021
 */
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ScheduleModule::class]
)
@ScheduleScope
interface ScheduleComponent {
    /**
     * Функция для предоставления интерактора
     *
     * @return реализацию интерфейса [ScheduleInteractor]
     */
    fun scheduleInteractor(): ScheduleInteractor
}