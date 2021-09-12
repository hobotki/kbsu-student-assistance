package com.snakelord.pets.kbsustudentassistance.presentation.common.theme

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.snakelord.pets.kbsustudentassistance.R
import javax.inject.Inject

class ThemeChanger @Inject constructor(
    private val context: Context,
    private val settingsSharedPreferences: SharedPreferences,
) {

    fun updateTheme() {
        val mode: Int = when(getCurrentTheme()) {
            LIGHT -> {
                AppCompatDelegate.MODE_NIGHT_NO
            }
            DARK -> {
                AppCompatDelegate.MODE_NIGHT_YES
            }
            else -> {
                getThemeDefaultBehaviour()
            }
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    private fun getThemeDefaultBehaviour(): Int {
        return if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
        } else {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
    }

    private fun getCurrentTheme(): String? {
        return settingsSharedPreferences.getString(context.getString(R.string.mode), null)
    }

    fun getSummaryByTheme(): Int {
        return when (getCurrentTheme()) {
            LIGHT -> {
                R.string.light_summary
            }
            DARK -> {
                R.string.dark_summary
            }
            else -> {
                R.string.system_default_summary
            }
        }
    }

    fun isDarkTheme(): Boolean {
        val currentTheme = getCurrentTheme()
        if (currentTheme == DARK) {
            return true
        }
        if (currentTheme == SYSTEM_DEFAULT) {
            return isSystemInDarkTheme()
        }
        return false
    }

    private fun isSystemInDarkTheme(): Boolean {
        val systemUIMode = context.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        return systemUIMode == Configuration.UI_MODE_NIGHT_YES
    }

    companion object {
        private const val SYSTEM_DEFAULT = "system"
        private const val LIGHT = "light"
        private const val DARK = "dark"
    }
}