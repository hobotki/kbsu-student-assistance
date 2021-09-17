package com.snakelord.pets.kbsustudentassistance.presentation.common.state

import com.snakelord.pets.kbsustudentassistance.domain.model.error.OperationError


/**
 * Класс, описывабщий состояние экрана во время операции
 *
 * @author Murad Luguev on 27-08-2021
 */
sealed class UIStates {
    /**
     * Загрузка данных
     */
    object Loading: UIStates()

    /**
     * Данные получены успешно
     */
    object Successful: UIStates()

    /**
     * Произошла ошибка во время выполнения операции
     *
     * @property error модель ошибки
     */
    data class Error(val error: OperationError): UIStates()
}