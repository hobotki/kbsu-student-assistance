package com.snakelord.pets.kbsustudentassistance.domain.interactor.login

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

/**
 * Интерактор для получения и обработки данных студента
 *
 * @author Murad Luguev on 27-08-2021
 */
interface LoginInteractor {

    /**
     * Функция для проверки корректности введенной фамилии студента
     *
     * @param secondName фамилия студента
     *
     * @return * [VerificationResult.FIELD_IS_EMPTY] если [String.isEmpty] вернёт [true];
     * * [VerificationResult.FIELD_IS_TOO_SHORT], если длинна фамилии не соответсвует минимальной;
     * * [VerificationResult.SUCCESSFUL], если введенные данные корректны
     */
    fun verifySecondName(secondName: String): VerificationResult

    /**
     * Функция для проверки корректности введенной фамилии студента
     *
     * @param recordBookNumber номер зачетной книжки студента
     *
     * @return * [VerificationResult.FIELD_IS_EMPTY] если [String.isEmpty] вернёт [true];
     * * [VerificationResult.FIELD_IS_TOO_SHORT], если длинна номера зачетной книжки
     *   не соответсвует минимальной;
     * * [VerificationResult.SUCCESSFUL], если введенные данные корректны
     */
    fun verifyRecordBookNumber(recordBookNumber: String): VerificationResult

    /**
     * Функция для авторизации студента
     *
     * @param secondName фамилия студента
     * @param recordBookNumber номер зачетной книжки студента
     * @return экземпляр [Single] с типом [StudentDto]
     */
    fun loginUser(secondName: String, recordBookNumber: String): Single<StudentDto>

    /**
     * Функция для сохранения информации о студенте в базу данных
     *
     * @param studentDto модель данных, которую вернет API
     *
     * @return экземпляр [Completable]
     */
    fun saveStudentInfo(studentDto: StudentDto): Completable

    /**
     * Функция для проверки авторизации пользователя
     *
     * @return экземпляр [Maybe] с параметром [StudentEntity]
     */
    fun isStudentLogined(): Maybe<StudentEntity>
}