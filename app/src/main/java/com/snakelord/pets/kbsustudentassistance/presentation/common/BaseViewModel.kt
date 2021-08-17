package com.snakelord.pets.kbsustudentassistance.presentation.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.model.Error
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel(
    private val errorMapper: Mapper<Throwable, Error>
) : ViewModel() {

    private val errorsMutableLivedata = MutableLiveData<Error>()
    val errors: LiveData<Error>
        get() = errorsMutableLivedata

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    protected fun performError(throwable: Throwable) {
        val error = errorMapper.map(throwable)
        errorsMutableLivedata.value = error
    }
}