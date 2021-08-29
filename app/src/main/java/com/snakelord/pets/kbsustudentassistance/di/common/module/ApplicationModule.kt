package com.snakelord.pets.kbsustudentassistance.di.common.module

import android.content.Context
import androidx.room.Room
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.Database
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.DatabaseConst
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.dao.student.StudentDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Dagger-модуль для предоставления зависимостей,
 * экземпляры которых можно создать напрямую
 *
 * @author Murad Luguev on 27-08-2021
 */
@Module
class ApplicationModule {

    /**
     * Функция для создания экземпляра [Database]
     *
     * @param context экземпляр класса [Context] для создания/получения файла базы данных
     * @return экземпляр [Database]
     */
    @Provides
    fun provideDatabase(context: Context): Database {
        return Room
            .databaseBuilder(context, Database::class.java, DatabaseConst.DATABASE_NAME)
            .build()
    }

    /**
     * Функция для создания экземпляра [OkHttpClient]
     *
     * @param httpLoggingInterceptor экземпляр [HttpLoggingInterceptor]
     * для вывода результатов запросов в лог
     *
     * @return экземпляр [OkHttpClient]
     */
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Функция для создания экземпляра [HttpLoggingInterceptor]
     *
     * @return экземпляр [HttpLoggingInterceptor]
     */
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    /**
     * Предоставляет экземпляр [StudentDao]
     * для работы с таблицей студента в базе данных
     *
     * @param database экземпляр базы данных [Database]
     *
     * @return экземпляр [StudentDao]
     */
    @Provides
    fun providesStudentDao(database: Database): StudentDao {
        return database.studentDao()
    }

    companion object {
        private const val CONNECTION_TIMEOUT = 3L
    }
}