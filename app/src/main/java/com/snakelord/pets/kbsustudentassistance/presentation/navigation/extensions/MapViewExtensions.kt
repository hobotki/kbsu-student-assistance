package com.snakelord.pets.kbsustudentassistance.presentation.navigation.extensions

import com.yandex.mapkit.mapview.MapView

fun MapView.setNightTheme(isDarkTheme: Boolean) {
    map.isNightModeEnabled = isDarkTheme
}