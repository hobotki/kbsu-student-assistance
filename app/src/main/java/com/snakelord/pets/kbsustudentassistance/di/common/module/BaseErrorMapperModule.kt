package com.snakelord.pets.kbsustudentassistance.di.common.module

import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.error.BaseErrorMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.error.OperationError
import dagger.Binds
import dagger.Module

/**
 * Dagger-модуль для предоставления базового маппера для отображения ошибок типа
 * [Mapper]<[Throwable], [OperationError]>
 *
 * @author Murad Luguev on 12-09-2021
 */
@Module
interface BaseErrorMapperModule {
    /**
     * Функция, которая связвывает заданную реализацию с интерфейсом [Mapper]
     *
     * @param baseErrorMapper реализация интерфейса [Mapper]
     *
     * @return экземпляр [Mapper] с параметрами [Throwable] и [OperationError]
     */
    @Binds
    fun bindsStudentErrorMapper(baseErrorMapper: BaseErrorMapper):
            Mapper<Throwable, OperationError>
}