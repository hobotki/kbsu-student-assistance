package com.snakelord.pets.kbsustudentassistance.domain.model.login

/**
 * Перечисление для отображения результата проверки введенных полей
 *
 * @author Murad Luguev on 27-08-2021
 */
enum class VerificationResult {
    /**
     * Введенное поле корректно
     */
    SUCCESSFUL,

    /**
     * Длина введенного поля не соответствует минимальной
     */
    FIELD_IS_TOO_SHORT,

    /**
     * Поле пусто
     */
    FIELD_IS_EMPTY,

    /**
     * Поле содержит недопустимые символы
     */
    FIELD_CONTAINS_INVALID_SYMBOLS;

    fun isSuccessful(): Boolean {
        return this == SUCCESSFUL
    }
}
