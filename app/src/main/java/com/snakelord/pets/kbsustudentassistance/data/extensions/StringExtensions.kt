package com.snakelord.pets.kbsustudentassistance.data.extensions

/**
 * Функция-расширение для проверки ответа с API
 *
 * @return [true] если ответ пришел пустым и [false] если ответ не пустой
 *
 * @author Murad Luguev on 27-08-2021
 */
fun String.responseIsEmpty(): Boolean {
    return this == "[]"
}