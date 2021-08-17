package com.snakelord.pets.kbsustudentassistance.di.common.module

import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProviderImpl
import dagger.Binds
import dagger.Module

@Module
interface SchedulersProviderModule {
    @Binds
    fun bindsSchedulerProvider(schedulersProviderImpl: SchedulersProviderImpl): SchedulersProvider
}