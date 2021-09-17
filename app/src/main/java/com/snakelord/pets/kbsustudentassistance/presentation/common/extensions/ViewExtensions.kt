package com.snakelord.pets.kbsustudentassistance.presentation.common.extensions

import android.animation.ValueAnimator
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * Функция-расширение для [View]
 *
 * Делает [View] видимой
 *
 * @author Murad Luguev on 27-08-2021
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Функция-расширение для [View]
 *
 * Делает [View] невидимой, но не освобождает место
 *
 * @author Murad Luguev on 11-09-2021
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * Функция-расширение для [View]
 *
 * Делает [View] невидимой и освобождает место, занимаемое ей
 *
 * @author Murad Luguev on 27-08-2021
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * Функция-расширение для [View]
 *
 * Анимирует [View] эффектом тряски
 *
 * @author Murad Luguev on 27-08-2021
 */
fun View.shake() {
    ValueAnimator.ofFloat( 0f, 8f, -8f, 0f).apply {
        repeatCount = 2
        duration = 250
        addUpdateListener {
            this@shake.translationX = it.animatedValue as Float
        }
    }.start()
}

/**
 * Функция-расширение для [View]
 *
 * Переводит [View] в состояние DISABLED, когда с ней не возможно взаимодейстовать
 *
 * @author Murad Luguev on 27-08-2021
 */
fun View.disable() {
    isEnabled = false
}

/**
 * Функция-расширение для [View]
 *
 * Переводит [View] в состояние ENABLED, когда с ней возможно взаимодействовать
 *
 * @author Murad Luguev on 27-08-2021
 */
fun View.enable() {
    isEnabled = true
}

/**
 * Функция-расширение для [TextInputLayout]
 *
 * Отображает текст ошибки, переданный в качестве параметра
 *
 * @param message текст ошибки, по умолчанию [null]
 *
 * @author Murad Luguev on 27-08-2021
 */
fun TextInputLayout.showError(message: String? = null) {
    isErrorEnabled = true
    if (message == null) {
        showErrorWithEmptyMessage()
    }
    else {
        getErrorTextView().visible()
        error = message
    }
    shake()
}

/**
 * Функция-расширение для [TextInputLayout]
 *
 * Переводит [TextInputLayout] в состояние отображения ошибки
 *
 * @author Murad Luguev on 27-08-2021
 */
fun TextInputLayout.showErrorWithEmptyMessage() {
    getErrorTextView()
        .gone()
    error = " "
}

/**
 * Функция-расширение для [TextInputLayout]
 *
 * Возвращает [View] для отображения текста ошибки
 * @return [View] для отображения текста ошибки
 *
 * @author Murad Luguev on 27-08-2021
 */
fun TextInputLayout.getErrorTextView(): View {
    return getChildAt(1)
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
fun TextInputEditText.textToString(): String {
    return text.toString()
}

/**
 * Поле расширение для [BottomSheetBehavior]
 *
 * * Принимает значение [trye] если [BottomSheetBehavior.state]
 *   равен [BottomSheetBehavior.STATE_EXPANDED]
 * * Принимает значение [false] во всех остальных случаях
 *
 * @param T наследуется от [View] для того,
 * чтобы любой экземпляр [BottomSheetBehavior]мог обратится к этой функции
 *
 * @author Murad Luguev on 27-08-2021
 */
val <T : View> BottomSheetBehavior<T>.isExpanded
    get() = (state == STATE_EXPANDED)

/**
 * Функция-расширения для [BottomSheetBehavior]
 *
 * Устанавливает значение [BottomSheetBehavior.state] равным [BottomSheetBehavior.STATE_EXPANDED]
 *
 * @param T наследуется от [View] для того,
 * чтобы любой экземпляр [BottomSheetBehavior] мог обратится к этой функции
 *
 * @author Murad Luguev on 27-08-2021
 */
fun <T : View> BottomSheetBehavior<T>.expand() {
    state = STATE_EXPANDED
}

/**
 * Функция-расширения для [BottomSheetBehavior]
 *
 * Устанавливает значение [BottomSheetBehavior.state] равным [BottomSheetBehavior.STATE_COLLAPSED]
 *
 * @param T наследуется от [View] для того,
 * чтобы любой экземпляр [BottomSheetBehavior] мог обратится к этой функции
 *
 * @author Murad Luguev on 27-08-2021
 */
fun <T : View> BottomSheetBehavior<T>.collapse() {
    state = STATE_COLLAPSED
}