package com.snakelord.pets.kbsustudentassistance.di.common.module

import androidx.lifecycle.ViewModelProvider
import com.snakelord.pets.kbsustudentassistance.di.common.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}