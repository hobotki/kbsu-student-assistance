package com.snakelord.pets.kbsustudentassistance.di.schedule.component

import com.snakelord.pets.kbsustudentassistance.di.common.component.ApplicationComponent
import com.snakelord.pets.kbsustudentassistance.di.schedule.module.ScheduleDaoModule
import com.snakelord.pets.kbsustudentassistance.di.schedule.module.ScheduleModule
import com.snakelord.pets.kbsustudentassistance.di.schedule.scope.ScheduleScope
import com.snakelord.pets.kbsustudentassistance.domain.interactor.schedule.ScheduleInteractor
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import dagger.Component

@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ScheduleModule::class, ScheduleDaoModule::class]
)
@ScheduleScope
interface ScheduleComponent {

    fun scheduleInteractor(): ScheduleInteractor

    fun schedulersProvider(): SchedulersProvider

}