package com.snakelord.pets.kbsustudentassistance.domain.interactor.login

import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.domain.model.login.VerificationResult
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
     * * [VerificationResult.FIELD_CONTAINS_INVALID_SYMBOLS] если фамилия содержит недопустимые
     * символы;
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
     *   * [VerificationResult.FIELD_CONTAINS_INVALID_SYMBOLS] если номер зачетной книжки
     *   содержит недопустимые символы;
     * * [VerificationResult.SUCCESSFUL], если введенные данные корректны
     */
    fun verifyRecordBookNumber(recordBookNumber: String): VerificationResult

    /**
     * Функция для авторизации студента
     *
     * @param secondName фамилия студента
     * @param recordBookNumber номер зачетной книжки студента
     *
     * @return данные студента типа [Single]<[StudentDto]>
     */
    fun loginStudent(secondName: String, recordBookNumber: String): Single<StudentDto>

    /**
     * Функция для сохранения информации о студенте в базу данных
     *
     * @param studentDto модель данных, которую вернет API
     *
     * @return результат операции типа [Completable]
     */
    fun saveStudentInfo(studentDto: StudentDto): Completable

    /**
     * Функция для проверки авторизации пользователя
     *
     * @return состояние авторизации студента типа [Maybe]<[StudentEntity]>
     */
    fun isStudentLogined(): Maybe<StudentEntity>
}