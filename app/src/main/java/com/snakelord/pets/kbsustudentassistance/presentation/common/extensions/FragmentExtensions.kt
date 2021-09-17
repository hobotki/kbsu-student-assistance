package com.snakelord.pets.kbsustudentassistance.presentation.common.extensions

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.fragment.app.Fragment
import com.snakelord.pets.kbsustudentassistance.presentation.main.NavigationCallback

/**
 * Поле-расширение для фрагментов
 *
 * Возвращает [NavigationCallback], который Activity-хост должно реализовывать
 *
 * @author Murad Luguev on 27-08-2021
 */
val Fragment.navigationCallback: NavigationCallback
    get() = activity as NavigationCallback

/**
 * Функция-расширение для фрагметов
 *
 * Устанавливает портретную ориентацию для фрагмента
 */
@SuppressLint("SourceLockedOrientationActivity")
fun Fragment.setPortraitOrientation() {
    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}

/**
 * Функция-расширение для фрагметов
 *
 * Устанавливает "свободную" ориентацию для фрагмента
 */
fun Fragment.setUnspecifiedOrientation() {
    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
}