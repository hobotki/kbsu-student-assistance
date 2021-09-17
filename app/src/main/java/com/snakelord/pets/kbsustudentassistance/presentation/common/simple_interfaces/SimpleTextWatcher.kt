package com.snakelord.pets.kbsustudentassistance.presentation.common.simple_interfaces

import android.text.Editable
import android.text.TextWatcher

/**
 * Базовая реализация интерфейса TextWatcher
 * для переопределения только необходимых методов
 *
 * @author Murad Luguev on 27-08-2021
 */
abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //Реализовать по мере необходимости
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        //Реализовать по мере необходимости
    }

    override fun afterTextChanged(s: Editable?) {
        //Реализовать по мере необходимости
    }
}