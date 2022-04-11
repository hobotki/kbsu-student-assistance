package com.snakelord.pets.kbsustudentassistance.presentation.settings.extensions

import android.content.SharedPreferences
import androidx.preference.PreferenceFragmentCompat
import java.lang.IllegalStateException

val PreferenceFragmentCompat.sharedPreferences: SharedPreferences
    get() = preferenceScreen.sharedPreferences
        ?: throw IllegalStateException("Unable to get preferences")
