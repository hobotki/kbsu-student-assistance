package com.snakelord.pets.kbsustudentassistance.data.datasource.api.student

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.BaseApiMapper
import com.snakelord.pets.kbsustudentassistance.data.extensions.responseIsEmpty
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.exception.BadResponseException
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
    private val loginMapper: Mapper<String, StudentDto>,
) : BaseApiMapper<StudentDto>(
    okHttpClient,
    loginMapper
), StudentLoginApi {

    override fun loginStudent(secondName: String, recordBookNumber: String): StudentDto {
        return executeRequest(secondName, recordBookNumber)
    }

    override fun generateRequest(vararg params: String): Request {
        val requestURL = "$BASE_URL?" +
                "$QUERY_RECORD_BOOK_PATH=${params[1]}&" +
                "$QUERY_SECOND_NAME_PATH=${params[0]}"
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
    }
}