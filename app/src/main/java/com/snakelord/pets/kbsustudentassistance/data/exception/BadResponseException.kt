package com.snakelord.pets.kbsustudentassistance.data.exception

/**
 * Исключение, выбрасываемое во время ошибки при обращении к API
 *
 * @property responseCode код ошибки
 *
 * @author Murad Luguev on 27-08-2021
 */
class BadResponseException(val responseCode: Int) : Exception()
