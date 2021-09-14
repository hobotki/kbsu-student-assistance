package com.snakelord.pets.kbsustudentassistance.domain.extensions

import java.util.regex.Pattern

/**
 * Функция-расширение для проверки ответа с API
 *
 * @return * [true] если ответ пришел пустым;
 * * [false] если ответ не пустой
 *
 * @author Murad Luguev on 27-08-2021
 */
fun String.responseIsEmpty(): Boolean {
    return this == "[]"
}

/**
 * Функция-расширение для проверки введенных пользователем данных
 *
 * @param pattern паттерн недопустимых символов
 *
 * @return * [true] если поле содержит недопустимые символы;
 *  * [false] если поле не содержит недопустимых символов
 *
 * @author Murad Luguev on 15-09-2021
 */
fun String.findInvalidSymbols(pattern: String): Boolean {
    val invalidSymbolsPattern = Pattern.compile(pattern)
    val matcher = invalidSymbolsPattern.matcher(this)
    return matcher.find()
}