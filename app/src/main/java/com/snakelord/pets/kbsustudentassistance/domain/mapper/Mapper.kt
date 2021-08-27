package com.snakelord.pets.kbsustudentassistance.domain.mapper

/**
 * Общий интерфейс маппера для преобразования входных данных в выходные
 *
 * @param I тип входных данных
 * @param O тип выходных данных
 *
 * @author Murad Luguev on 27-08-2021
 */
interface Mapper<I, O> {
    /**
     * Функция, которая преобразует входные данные типа [I] в тип [O]
     *
     * @param input входные данные
     * @return экземпляр [O]
     */
    fun map(input: I) : O
}