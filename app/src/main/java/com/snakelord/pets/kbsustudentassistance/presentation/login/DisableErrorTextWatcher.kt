package com.snakelord.pets.kbsustudentassistance.presentation.login

import com.google.android.material.textfield.TextInputLayout
import com.snakelord.pets.kbsustudentassistance.common.simple_interfaces.SimpleTextWatcher

class DisableErrorTextWatcher(private val textInputLayout: TextInputLayout) : SimpleTextWatcher() {

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        textInputLayout.isErrorEnabled = false
    }

}