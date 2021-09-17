package com.snakelord.pets.kbsustudentassistance.presentation.common.extensions

import android.content.Context
import android.content.pm.PackageManager

/**
 * Функция-расширение для [Context]
 *
 * Проверяет установленно ли приложение на устройстве
 *
 * @param packageName имя пакета приложения
 *
 * @return * [true] если приложение установлено
 * * [false] если приложение не установлено или отключено
 */
fun Context.isPackageExist(packageName: String): Boolean {
    return try {
        val appInfo = packageManager.getApplicationInfo(packageName,0)
        return appInfo.enabled
    } catch (exception: PackageManager.NameNotFoundException) {
        false
    }
}