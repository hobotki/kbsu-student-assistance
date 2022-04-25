package com.snakelord.pets.kbsustudentassistance.presentation.common.extensions

import android.animation.ValueAnimator
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED

/**
 * Функция-расширение для [View]
 *
 * Делает [View] видимой
 *
 * @author Murad Luguev on 27-08-2021
 */
@Deprecated("Использовать свойство View.isVisible")
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
@Deprecated("Использовать свойство View.isVisible")
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
@Deprecated("Использовать свойство View.isVisible")
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
