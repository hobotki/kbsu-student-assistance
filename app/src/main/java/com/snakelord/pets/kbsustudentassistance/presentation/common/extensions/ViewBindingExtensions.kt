package com.snakelord.pets.kbsustudentassistance.presentation.common.extensions

import androidx.viewbinding.ViewBinding
import java.lang.IllegalStateException
import kotlin.jvm.Throws

/**
 * Функция-расширение для предоставления non-nullable вью-биндинга
 *
 * @param T тип ViewBinding
 * @param viewBinding nullable экземпляр биндинга
 * @throws IllegalStateException если [viewBinding] равен null
 * @return non-nullable экземпляр вью-биндинга
 *
 * @author Murad Luguev on 14-04-2022
 */
@Throws(IllegalStateException::class)
fun <T: ViewBinding> requireViewBinding(viewBinding: T?): T {
    return viewBinding ?: throw IllegalStateException("$viewBinding doesn't inflated or binded")
}
