package com.snakelord.pets.kbsustudentassistance.data.datasource.api

import com.snakelord.pets.kbsustudentassistance.common.extensions.responseIsEmpty
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.exception.BadResponseException
import com.snakelord.pets.kbsustudentassistance.data.mapper.student.StudentLoginMapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject

/**
 * Реализация интерфейса [StudentLoginApi]
 *
 * @property okHttpClient клиент для HTTP запросов
 * @property loginMapper маппер для преобразования ответа в экземпляр [StudentDto]
 *
 * @author Murad Luguev on 27-08-2021
 */
class StudentLoginApiImpl @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val loginMapper: Mapper<String, StudentDto>
) : StudentLoginApi {

    @Throws(BadResponseException::class, IOException::class, IllegalStateException::class)
    override fun loginStudent(secondName: String, recordBookNumber: String): StudentDto {
        val response = okHttpClient
            .newCall(generateRequest(secondName, recordBookNumber))
            .execute()
        if (!response.isSuccessful) {
            throw BadResponseException(response.code)
        }
        val responseBody = response.body!!.string()
        if (responseBody.responseIsEmpty()) {
            throw BadResponseException(STUDENT_NOT_FOUND)
        }
        return loginMapper.map(responseBody)
    }

    private fun generateRequest(secondName: String, recordBookNumber: String): Request {
        val requestURL = "$BASE_URL?" +
                "$QUERY_RECORD_BOOK_PATH=$recordBookNumber&" +
                "$QUERY_SECOND_NAME_PATH=$secondName"
        return Request.Builder()
            .url(requestURL)
            .get()
            .build()
    }

    companion object {
        private const val BASE_URL =
            "https://my-json-server.typicode.com/snakelord757/FakeUniversityDB/students/"
        
        private const val QUERY_RECORD_BOOK_PATH = "record_book_number"
        private const val QUERY_SECOND_NAME_PATH = "second_name"
        private const val STUDENT_NOT_FOUND = 404
    }
}