package com.snakelord.pets.kbsustudentassistance.presentation.settings.extensions

import android.content.SharedPreferences
import androidx.preference.PreferenceFragmentCompat


val PreferenceFragmentCompat.sharedPreferences: SharedPreferences
    get() = preferenceScreen.sharedPreferences