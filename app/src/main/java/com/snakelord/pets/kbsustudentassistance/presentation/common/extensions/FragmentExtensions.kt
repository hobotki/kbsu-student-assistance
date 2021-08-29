package com.snakelord.pets.kbsustudentassistance.presentation.common.extensions

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