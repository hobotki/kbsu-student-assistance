package com.snakelord.pets.kbsustudentassistance.presentation.application

import android.app.Application
import com.snakelord.pets.kbsustudentassistance.di.common.component.ApplicationComponent
import com.snakelord.pets.kbsustudentassistance.di.common.component.DaggerApplicationComponent

class KbsuStudentAssistanceApp : Application() {

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .context(context = applicationContext)
            .build()
    }

    companion object {
        lateinit var applicationComponent: ApplicationComponent
            private set
    }
}