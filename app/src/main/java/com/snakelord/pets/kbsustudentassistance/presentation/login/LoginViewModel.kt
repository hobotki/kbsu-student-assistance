package com.snakelord.pets.kbsustudentassistance.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult
import com.snakelord.pets.kbsustudentassistance.domain.interactor.login.LoginInteractor
import com.snakelord.pets.kbsustudentassistance.domain.mapper.error.StudentErrorMapper
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import com.snakelord.pets.kbsustudentassistance.presentation.common.state.UIStates
import com.snakelord.pets.kbsustudentassistance.presentation.common.viewmodel.BaseViewModel

class LoginViewModel(
    private val loginInteractor: LoginInteractor,
    private val schedulersProvider: SchedulersProvider,
    studentErrorMapper: StudentErrorMapper
) : BaseViewModel(studentErrorMapper) {

    init {
        checkIsStudentLogined()
    }

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
        updateUIState(UIStates.Loading)
        val loginDisposable = loginInteractor.loginUser(secondName, recordBookNumber)
            .observeOn(schedulersProvider.main())
            .subscribeOn(schedulersProvider.io())
            .subscribe(
                { studentDto -> saveStudent(studentDto) },
                { throwable -> performError(throwable) }
            )
        compositeDisposable.add(loginDisposable)
    }

    private fun saveStudent(studentDto: StudentDto) {
        val saveStudentDisposable = loginInteractor.saveStudentInfo(studentDto)
            .observeOn(schedulersProvider.main())
            .subscribeOn(schedulersProvider.io())
            .subscribe({
                updateUIState(UIStates.Successful)
            },
                { throwable -> performError(throwable) }
            )
        compositeDisposable.add(saveStudentDisposable)
    }

    private fun checkIsStudentLogined() {
        val checkDisposable = loginInteractor.isStudentLogined()
            .observeOn(schedulersProvider.main())
            .subscribeOn(schedulersProvider.io())
            .subscribe(
                { updateUIState(UIStates.Successful) },
                { throwable -> performError(throwable) }
            )
        compositeDisposable.add(checkDisposable)
    }
}