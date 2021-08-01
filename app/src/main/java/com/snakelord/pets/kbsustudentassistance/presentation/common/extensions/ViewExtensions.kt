package com.snakelord.pets.kbsustudentassistance.presentation.common.extensions

import android.animation.ValueAnimator
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
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

fun View.disable() {
    isEnabled = false
}

fun View.enable() {
    isEnabled = true
}

fun TextInputLayout.showError(message: String? = null) {
    isErrorEnabled = true
    if (message != null)
        error = message
    shake()
}

fun Snackbar.moveToTop(): Snackbar {
    val snackbarLayoutParams = view.layoutParams as FrameLayout.LayoutParams
    snackbarLayoutParams.gravity = Gravity.TOP
    view.layoutParams = snackbarLayoutParams
    return this
}

fun TextInputEditText.textToString(): String = text.toString()