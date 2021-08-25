package com.snakelord.pets.kbsustudentassistance.presentation.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.model.OperationError
import com.snakelord.pets.kbsustudentassistance.presentation.common.state.UIStates
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel(
    private val errorMapper: Mapper<Throwable, OperationError>?
) : ViewModel() {

    private val uiStatesMutableLiveData = MutableLiveData<UIStates>()
    val uiStates: LiveData<UIStates>
        get() = uiStatesMutableLiveData

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    protected fun updateUIState(uiStates: UIStates) {
        uiStatesMutableLiveData.value = uiStates
    }

    protected fun performError(throwable: Throwable) {
        errorMapper?.run {
            val error = map(throwable)
            uiStatesMutableLiveData.value = UIStates.Error(error)
        }
    }
}