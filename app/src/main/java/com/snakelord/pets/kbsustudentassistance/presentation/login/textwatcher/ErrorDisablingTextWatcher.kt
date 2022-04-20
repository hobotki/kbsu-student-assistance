package com.snakelord.pets.kbsustudentassistance.presentation.login.textwatcher

import android.text.Editable
import com.google.android.material.textfield.TextInputLayout

class ErrorDisablingTextWatcher(
    private val textInputLayout: TextInputLayout
) : SimpleTextWatcher() {
    override fun afterTextChanged(s: Editable?) {
        textInputLayout.isErrorEnabled = false
        textInputLayout.editText?.removeTextChangedListener(this)
    }
}
