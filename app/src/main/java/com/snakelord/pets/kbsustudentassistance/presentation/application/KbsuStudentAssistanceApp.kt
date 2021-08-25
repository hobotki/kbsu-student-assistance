package com.snakelord.pets.kbsustudentassistance.presentation.application

import android.app.Application
import com.snakelord.pets.kbsustudentassistance.BuildConfig
import com.snakelord.pets.kbsustudentassistance.di.common.component.ApplicationComponent
import com.snakelord.pets.kbsustudentassistance.di.common.component.DaggerApplicationComponent
import com.yandex.mapkit.MapKitFactory
import io.reactivex.rxjava3.core.Single
import java.util.*

class KbsuStudentAssistanceApp : Application() {

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .context(context = applicationContext)
            .build()
        MapKitFactory.setApiKey(BuildConfig.YANDEX_MAP_API_KEY)
        MapKitFactory.initialize(applicationContext)
    }

    companion object {
        lateinit var applicationComponent: ApplicationComponent
            private set
    }
}