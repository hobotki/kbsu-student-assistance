package com.snakelord.pets.kbsustudentassistance.presentation.login.extensions

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.shake

/**
 * Функция-расширение для [TextInputLayout]
 *
 * Отображает текст ошибки, переданный в качестве параметра
 *
 * @param message текст ошибки
 *
 * @author Murad Luguev on 27-08-2021
 */
fun TextInputLayout.showError(message: String) {
    if (!isErrorEnabled) {
        isErrorEnabled = true
        error = message
        shake()
    }
}

/**
 * Функция-расширение для [TextInputEditText]
 *
 * Преобразует [Editable] в [String]
 *
 * @return содержимое [TextInputEditText] в виде строки
 *
 * @author Murad Luguev on 27-08-2021
 */
fun TextInputEditText.fieldAsString(): String {
    return text.toString()
}
