package com.snakelord.pets.kbsustudentassistance.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snakelord.pets.kbsustudentassistance.data.model.State
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult
import com.snakelord.pets.kbsustudentassistance.domain.interactor.LoginInteractor
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginViewModel : ViewModel() {

    private val loginInteractor = LoginInteractor()

    private val studentLoginResult = MutableLiveData<State>()
    val loginResult: LiveData<State>
        get() = studentLoginResult

    private val secondNameVerificationResult = MutableLiveData<VerificationResult>()
    val secondNameVerification: LiveData<VerificationResult>
        get() = secondNameVerificationResult

    private val recordBookVerificationResult = MutableLiveData<VerificationResult>()
    val recordBookVerification: LiveData<VerificationResult>
        get() = recordBookVerificationResult

    fun loginStudent(secondName: String, recordBookNumber: String) {
        secondNameVerificationResult.value = loginInteractor.verifySecondName(secondName)
        recordBookVerificationResult.value = loginInteractor.verifyRecordBookNumber(recordBookNumber)
        if (secondNameVerificationResult.value == VerificationResult.SUCCESSFUL &&
            recordBookVerificationResult.value == VerificationResult.SUCCESSFUL
        ) {
            login(secondName, recordBookNumber)
        }
    }

    private fun login(secondName: String, recordBookNumber: String) {
        loginInteractor.loginUser(secondName, recordBookNumber)
            .observeOn(Schedulers.io())
            .subscribe { student -> studentLoginResult.postValue(student) }
    }
}