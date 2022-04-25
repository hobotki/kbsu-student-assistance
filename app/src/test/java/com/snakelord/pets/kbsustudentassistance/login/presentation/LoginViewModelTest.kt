package com.snakelord.pets.kbsustudentassistance.login.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.student.model.StudentDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.database.entity.student.StudentEntity
import com.snakelord.pets.kbsustudentassistance.data.exception.BadResponseException
import com.snakelord.pets.kbsustudentassistance.domain.interactor.login.LoginInteractor
import com.snakelord.pets.kbsustudentassistance.domain.mapper.Mapper
import com.snakelord.pets.kbsustudentassistance.domain.mapper.error.BaseErrorMapper
import com.snakelord.pets.kbsustudentassistance.domain.model.error.OperationError
import com.snakelord.pets.kbsustudentassistance.domain.model.login.VerificationResult
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProvider
import com.snakelord.pets.kbsustudentassistance.presentation.common.schedulers.SchedulersProviderTest
import com.snakelord.pets.kbsustudentassistance.presentation.common.state.UIStates
import com.snakelord.pets.kbsustudentassistance.presentation.login.LoginViewModel
import io.mockk.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.InterruptedIOException
import java.net.UnknownHostException

class LoginViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    private val loginInteractor: LoginInteractor = mockk(relaxed = true)
    private val schedulersProvider: SchedulersProvider = SchedulersProviderTest()
    private val studentErrorMapper: Mapper<Throwable, OperationError> = BaseErrorMapper()
    private lateinit var loginViewModel: LoginViewModel

    private val uiStatesObserver: Observer<UIStates> = mockk()
    private val secondNameVerificationResultObserver: Observer<VerificationResult> = mockk()
    private val recordBookNumberVerificationResultObserver: Observer<VerificationResult> = mockk()

    @Before
    fun setUp() {
        every { uiStatesObserver.onChanged(any()) } just Runs
        every { secondNameVerificationResultObserver.onChanged(any()) } just Runs
        every { recordBookNumberVerificationResultObserver.onChanged(any()) } just Runs

        loginViewModel = LoginViewModel(loginInteractor, schedulersProvider, studentErrorMapper)

        loginViewModel.uiStates.observeForever(
            uiStatesObserver
        )
        loginViewModel.secondNameVerification.observeForever(
            secondNameVerificationResultObserver
        )
        loginViewModel.recordBookVerification.observeForever(
            recordBookNumberVerificationResultObserver
        )
    }

    @Test
    fun initStudentNotLoginedTest() {
        //Arrange
        every { loginInteractor.isStudentLogined() } returns Maybe.empty()

        //Assert
        verifySequence {
            uiStatesObserver wasNot Called
            secondNameVerificationResultObserver wasNot Called
            recordBookNumberVerificationResultObserver wasNot Called
        }
    }

    @Test
    fun initStudentLoginedTest() {
        //Arrange
        val expectedStudentEntity = STUDENT_ENTITY
        every { loginInteractor.isStudentLogined() } returns Maybe.just(expectedStudentEntity)

        loginViewModel = LoginViewModel(loginInteractor, schedulersProvider, studentErrorMapper)
        loginViewModel.uiStates.observeForever(
            uiStatesObserver
        )
        loginViewModel.secondNameVerification.observeForever(
            secondNameVerificationResultObserver
        )
        loginViewModel.recordBookVerification.observeForever(
            recordBookNumberVerificationResultObserver
        )

        //Assert
        verifySequence {
            uiStatesObserver.onChanged(any())
            secondNameVerificationResultObserver wasNot Called
            recordBookNumberVerificationResultObserver wasNot Called
        }
    }

    @Test
    fun loginTestEmptyFieldsTest() {
        //Arrange
        every { loginInteractor.verifySecondName(any()) } returns
                EMPTY_FIELDS_VERIFICATION_RESULT
        every { loginInteractor.verifyRecordBookNumber(any()) } returns
                EMPTY_FIELDS_VERIFICATION_RESULT

        //Act
        loginViewModel.loginStudent(STUDENT_SECOND_NAME, STUDENT_RECORD_BOOK_NUMBER)

        //Assert
        verifySequence {
            uiStatesObserver wasNot Called
            secondNameVerificationResultObserver.onChanged(EMPTY_FIELDS_VERIFICATION_RESULT)
            recordBookNumberVerificationResultObserver.onChanged(EMPTY_FIELDS_VERIFICATION_RESULT)
        }
    }

    @Test
    fun loginTestShortFieldsTest() {
        //Arrange
        every { loginInteractor.verifySecondName(any()) } returns
                SHORT_FIELDS_VERIFICATION_RESULT
        every { loginInteractor.verifyRecordBookNumber(any()) } returns
                SHORT_FIELDS_VERIFICATION_RESULT

        //Act
        loginViewModel.loginStudent(STUDENT_SECOND_NAME, STUDENT_RECORD_BOOK_NUMBER)

        //Assert
        verifySequence {
            uiStatesObserver wasNot Called
            secondNameVerificationResultObserver.onChanged(SHORT_FIELDS_VERIFICATION_RESULT)
            recordBookNumberVerificationResultObserver.onChanged(SHORT_FIELDS_VERIFICATION_RESULT)
        }
    }

    @Test
    fun loginSuccessTest() {
        //Arrange
        every { loginInteractor.verifySecondName(any()) } returns VERIFICATION_SUCCESSFUL
        every { loginInteractor.verifyRecordBookNumber(any()) } returns VERIFICATION_SUCCESSFUL
        every { loginInteractor.loginStudent(any(), any()) } returns
                Single.fromCallable { STUDENT_DTO }
        every { loginInteractor.saveStudentInfo(any()) } returns Completable.complete()
        val expectedUIState = STATE_SUCCESSFUL

        //Act
        loginViewModel.loginStudent(STUDENT_SECOND_NAME, STUDENT_RECORD_BOOK_NUMBER)

        //Assert
        verifySequence {
            uiStatesObserver.onChanged(STATE_LOADING)
            uiStatesObserver.onChanged(expectedUIState)
            secondNameVerificationResultObserver wasNot Called
            recordBookNumberVerificationResultObserver wasNot Called
        }
    }

    @Test
    fun connectionErrorTest() {
        //Arrange
        every { loginInteractor.verifySecondName(any()) } returns VERIFICATION_SUCCESSFUL
        every { loginInteractor.verifyRecordBookNumber(any()) } returns VERIFICATION_SUCCESSFUL
        every { loginInteractor.loginStudent(any(), any()) } returns Single.error(
            UnknownHostException()
        )
        val expectedUIState = UIStates.Error(CONNECTION_ERROR)

        //Act
        loginViewModel.loginStudent(STUDENT_SECOND_NAME, STUDENT_RECORD_BOOK_NUMBER)

        //Assert
        verifySequence {
            uiStatesObserver.onChanged(STATE_LOADING)
            uiStatesObserver.onChanged(expectedUIState)
        }
    }

    @Test
    fun connectionTimeoutErrorTest() {
        //Arrange
        every { loginInteractor.verifySecondName(any()) } returns VERIFICATION_SUCCESSFUL
        every { loginInteractor.verifyRecordBookNumber(any()) } returns VERIFICATION_SUCCESSFUL
        every { loginInteractor.loginStudent(any(), any()) } returns
                Single.error(InterruptedIOException())
        val expectedUIState = UIStates.Error(CONNECTION_TIMEOUT)

        //Act
        loginViewModel.loginStudent(STUDENT_SECOND_NAME, STUDENT_RECORD_BOOK_NUMBER)

        //Assert
        verifySequence {
            uiStatesObserver.onChanged(STATE_LOADING)
            uiStatesObserver.onChanged(expectedUIState)
        }
    }

    @Test
    fun badResponseErrorTest() {
        //Arrange
        every { loginInteractor.verifySecondName(any()) } returns VERIFICATION_SUCCESSFUL
        every { loginInteractor.verifyRecordBookNumber(any()) } returns VERIFICATION_SUCCESSFUL
        every { loginInteractor.loginStudent(any(), any()) } returns
                Single.error(BadResponseException(404))
        val expectedUIState = UIStates.Error(BAD_RESPONSE_ERROR)

        //Act
        loginViewModel.loginStudent(STUDENT_SECOND_NAME, STUDENT_RECORD_BOOK_NUMBER)

        //Assert
        verifySequence {
            uiStatesObserver.onChanged(STATE_LOADING)
            uiStatesObserver.onChanged(expectedUIState)
        }
    }

    @Test
    fun illegalStateErrorTest() {
        every { loginInteractor.verifySecondName(any()) } returns VERIFICATION_SUCCESSFUL
        every { loginInteractor.verifyRecordBookNumber(any()) } returns VERIFICATION_SUCCESSFUL
        every { loginInteractor.loginStudent(any(), any()) } returns
                Single.error(IllegalStateException())
        val expectedUIState = UIStates.Error(ILLEGAL_STATE_ERROR)

        //Act
        loginViewModel.loginStudent(STUDENT_SECOND_NAME, STUDENT_RECORD_BOOK_NUMBER)

        //Assert
        verifySequence {
            uiStatesObserver.onChanged(STATE_LOADING)
            uiStatesObserver.onChanged(expectedUIState)
        }
    }

    companion object {
        private const val STUDENT_SECOND_NAME = "Иванов"
        private const val STUDENT_RECORD_BOOK_NUMBER = "1800218"

        private val EMPTY_FIELDS_VERIFICATION_RESULT = VerificationResult.FIELD_IS_EMPTY
        private val SHORT_FIELDS_VERIFICATION_RESULT = VerificationResult.FIELD_IS_TOO_SHORT
        private val VERIFICATION_SUCCESSFUL = VerificationResult.SUCCESSFUL

        private val STUDENT_DTO = StudentDto(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialityCode = "09.03.01-3"
        )

        private val STUDENT_ENTITY = StudentEntity(
            fullName = "Иванов Иван Иванович",
            id = 3,
            specialityCode = "09.03.01-3"
        )

        private val STATE_LOADING = UIStates.Loading
        private val STATE_SUCCESSFUL = UIStates.Successful

        private val CONNECTION_ERROR = OperationError(R.string.failed_to_connect_to_server)
        private val CONNECTION_TIMEOUT = OperationError(R.string.connection_timeout)
        private val BAD_RESPONSE_ERROR = OperationError(R.string.unable_to_recognize_user)
        private val ILLEGAL_STATE_ERROR = OperationError(R.string.request_illegal_state)
    }
}

