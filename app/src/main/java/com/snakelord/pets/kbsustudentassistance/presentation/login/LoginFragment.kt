package com.snakelord.pets.kbsustudentassistance.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentLoginBinding
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult
import com.snakelord.pets.kbsustudentassistance.domain.interactor.LoginInteractor
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.gone
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.showError
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.visible

class LoginFragment : Fragment() {

    private var loginFragmentBinding: FragmentLoginBinding? = null
    private val binding
        get() = loginFragmentBinding!!

    private val loginInteractor = LoginInteractor()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        loginFragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.secondName
            .addTextChangedListener(DisableErrorTextWatcher(binding.secondNameTextInputLayout))

        binding.recordBookNumber
            .addTextChangedListener(DisableErrorTextWatcher(binding.recordBookNumberTextInputLayout))

        binding.loginButton.setOnClickListener { verifyFields() }
    }

    private fun verifyFields() {
        val secondName = binding.secondName.text.toString()
        val recordBookNumber = binding.recordBookNumber.text.toString()
        val secondNameVerificationResult = loginInteractor.verifySecondName(secondName)
        val recordBookNumberVerificationResult = loginInteractor.verifyRecordBookNumber(recordBookNumber)
        if (secondNameVerificationResult == VerificationResult.SUCCESSFUL &&
            recordBookNumberVerificationResult == VerificationResult.SUCCESSFUL)
            hideAll()
        else {
            showSecondNameFieldError(secondNameVerificationResult)
            showRecordBookNumberFieldError(recordBookNumberVerificationResult)
        }
    }

    private fun showSecondNameFieldError(result: VerificationResult) {
        if (result == VerificationResult.FIELD_IS_TOO_SHORT)
            binding.secondNameTextInputLayout.showError(getString(R.string.second_name_too_short))
        if (result == VerificationResult.FIELD_IS_EMPTY)
            binding.secondNameTextInputLayout.showError(getString(R.string.second_name_is_empty))
    }

    private fun showRecordBookNumberFieldError(result: VerificationResult) {
        if (result == VerificationResult.FIELD_IS_TOO_SHORT)
            binding.recordBookNumberTextInputLayout.showError(getString(R.string.record_book_too_short))
        if (result == VerificationResult.FIELD_IS_EMPTY)
            binding.recordBookNumberTextInputLayout.showError(getString(R.string.record_book_is_empty))
    }

    private fun hideAll() {
        binding.appName.gone()
        binding.secondNameTextInputLayout.gone()
        binding.recordBookNumberTextInputLayout.gone()
        binding.loginButton.gone()
    }

    private fun showAll() {
        binding.appName.visible()
        binding.secondNameTextInputLayout.visible()
        binding.recordBookNumberTextInputLayout.visible()
        binding.loginButton.visible()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val isErrorEnabledSecondName = binding.secondNameTextInputLayout.isErrorEnabled
        val isErrorEnabledRecordBookNumber = binding.recordBookNumberTextInputLayout.isErrorEnabled
        if (isErrorEnabledSecondName) {
            outState.putBoolean(SECOND_NAME_ERROR_ENABLED, isErrorEnabledSecondName)
            outState.putString(SECOND_NAME_ERROR_MESSAGE, binding.secondNameTextInputLayout.error.toString())
        }
        if (isErrorEnabledRecordBookNumber) {
            outState.putBoolean(RECORD_BOOK_NUMBER_ERROR_ENABLED, isErrorEnabledSecondName)
            outState.putString(RECORD_BOOK_NUMBER_ERROR_MESSAGE,
                binding.recordBookNumberTextInputLayout.error.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { bundle ->
            if (bundle.containsKey(SECOND_NAME_ERROR_ENABLED))
                binding.secondNameTextInputLayout.showError(bundle.getString(SECOND_NAME_ERROR_MESSAGE))
            if (bundle.containsKey(RECORD_BOOK_NUMBER_ERROR_ENABLED))
                binding.recordBookNumberTextInputLayout.showError(bundle.getString(RECORD_BOOK_NUMBER_ERROR_MESSAGE))
        }
    }

    companion object {
        private const val SECOND_NAME_ERROR_ENABLED = "second_name_error_enabled"
        private const val RECORD_BOOK_NUMBER_ERROR_ENABLED = "record_book_number_error_enabled"
        private const val SECOND_NAME_ERROR_MESSAGE = "second_name_error_message"
        private const val RECORD_BOOK_NUMBER_ERROR_MESSAGE = "record_book_number_error_message"
    }
}