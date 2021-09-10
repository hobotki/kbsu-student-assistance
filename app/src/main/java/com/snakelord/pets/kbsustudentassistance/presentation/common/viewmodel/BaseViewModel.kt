package com.snakelord.pets.kbsustudentassistance.presentation.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.model.OperationError
import com.snakelord.pets.kbsustudentassistance.presentation.common.state.UIStates
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Базовая ViewModel
 * содрежит функционал для обработки ошибок и изменения [UIStates]
 *
 * @property errorMapper маппер ошибок [Mapper] с параметрами [Throwable] и [OperationError].
 *
 * @author Murad Luguev on 27-08-2021
 */
abstract class BaseViewModel(
    private val errorMapper: Mapper<Throwable, OperationError>? = null
) : ViewModel() {

    private val uiStatesMutableLiveData = MutableLiveData<UIStates>()
    val uiStates: LiveData<UIStates>
        get() = uiStatesMutableLiveData

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    /**
     * Функция для обновления значения у [uiStatesMutableLiveData]
     *
     * @param uiState текущее состояние экрана
     */
    protected fun updateUIState(uiState: UIStates) {
        uiStatesMutableLiveData.value = uiState
    }

    /**
     * Функция для обработки исключений, возникших во время операций
     *
     * @param throwable исключение
     */
    protected fun performError(throwable: Throwable) {
        errorMapper?.let { mapper ->
            val error = mapper.map(throwable)
            updateUIState(UIStates.Error(error))
        }
    }
}