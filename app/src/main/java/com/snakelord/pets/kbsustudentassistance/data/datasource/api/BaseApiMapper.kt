package com.snakelord.pets.kbsustudentassistance.data.datasource.api

import com.snakelord.pets.kbsustudentassistance.data.exception.BadResponseException
import com.snakelord.pets.kbsustudentassistance.domain.extensions.responseIsEmpty
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

/**
 * Маппер для работы с API
 *
 * @param T параметр возвращаемого API
 * @property okHttpClient клиент для HTTP запросов
 * @property responseMapper маппер для перобразования JSON в [T]
 *
 * @author Murad Luguev on 12-09-2021
 */
abstract class BaseApiMapper<T>(
    private val okHttpClient: OkHttpClient,
    private val responseMapper: Mapper<String, T>
) {
    /**
     * Функция для выполнения запроса
     *
     * @param params параметры для создания запроса типа [Request]
     *
     * @return ответ с API типа [T]
     */
    @Throws(BadResponseException::class, IOException::class, IllegalStateException::class)
    protected fun executeRequest(vararg params: String): T {
        val response = okHttpClient
            .newCall(generateRequest(*params))
            .execute()

        if (!response.isSuccessful) {
            throw BadResponseException(response.code)
        }

        val responseBody = response.body ?: throw IllegalStateException()

        val responseContent = responseBody.string()

        if (responseContent.responseIsEmpty()) {
            throw BadResponseException(ANSWER_NOT_FOUND)
        }

        return responseMapper.map(responseContent)
    }

    /**
     * Функция для генерации запроса
     *
     * @param params параметры для генерации запроса
     *
     * @return запрос типа [Request]
     */
    abstract fun generateRequest(vararg params: String): Request

    companion object {
        private const val ANSWER_NOT_FOUND = 404
    }
}