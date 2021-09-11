package com.snakelord.pets.kbsustudentassistance.domain.interactor.pass

import android.graphics.Bitmap
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import io.reactivex.rxjava3.core.Single

/**
 * Интерактор для работы с данными студента и генерации QR-кода
 *
 * @author Murad Luguev on 11-09-2021
 */
interface PassInteractor {
    /**
     * Функция, которая возвращает данные студента
     *
     * @return данные студента типа [Single]<[Student]>
     */
    fun getStudentData(): Single<Student>

    /**
     * Функция, которая возвращает QR-код, сгенерированный по данным студента
     *
     * @param student данные студента
     * @param size размер ImageView, в который будет помещён QR-код
     *
     * @return сгенерированный QR-код типа [Single]<[Bitmap]>
     */
    fun getQrCode(student: Student, size: Int): Single<Bitmap>
}