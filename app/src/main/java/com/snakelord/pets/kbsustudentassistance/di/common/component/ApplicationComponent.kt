package com.snakelord.pets.kbsustudentassistance.di.common.component

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.Database
import com.snakelord.pets.kbsustudentassistance.di.common.module.ApplicationModule
import com.snakelord.pets.kbsustudentassistance.di.common.module.SchedulersProviderModule
import com.snakelord.pets.kbsustudentassistance.di.common.module.ViewModelFactoryModule
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient

@Component(
    modules = [ApplicationModule::class,
        ViewModelFactoryModule::class,
        SchedulersProviderModule::class]
)
interface ApplicationComponent {

    fun schedulersProvider(): SchedulersProvider

    fun viewModelFactory(): ViewModelProvider.Factory

    fun okHttpClient(): OkHttpClient

    fun database(): Database

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }
}