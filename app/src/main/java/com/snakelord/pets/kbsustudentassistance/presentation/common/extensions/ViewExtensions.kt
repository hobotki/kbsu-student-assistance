package com.snakelord.pets.kbsustudentassistance.presentation.common.extensions

import android.animation.ValueAnimator
import android.view.View
import com.google.android.material.textfield.TextInputLayout

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.shake() {
    ValueAnimator.ofFloat( 0f, 8f, -8f, 0f).apply {
        repeatCount = 2
        duration = 250
        addUpdateListener {
            this@shake.translationX = it.animatedValue as Float
        }
    }.start()
}


fun TextInputLayout.showError(message: String?) {
    isErrorEnabled = true
    error = message
    shake()
}