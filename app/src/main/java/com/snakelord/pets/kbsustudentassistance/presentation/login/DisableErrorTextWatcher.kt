package com.snakelord.pets.kbsustudentassistance.presentation.login

import com.google.android.material.textfield.TextInputLayout
import com.snakelord.pets.kbsustudentassistance.common.simple_interfaces.SimpleTextWatcher

/**
 * TextWatcher необходимый для отключения отображения ошибки во время ввода
 *
 * @property textInputLayout контейнер, отображающий ошибку
 *
 * @author Murad Luguev on 27-08-2021
 */
class DisableErrorTextWatcher(private val textInputLayout: TextInputLayout) : SimpleTextWatcher() {

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        textInputLayout.isErrorEnabled = false
    }

}