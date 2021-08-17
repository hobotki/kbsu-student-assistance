package com.snakelord.pets.kbsustudentassistance.presentation.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentLoginBinding
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult
import com.snakelord.pets.kbsustudentassistance.domain.model.Error
import com.snakelord.pets.kbsustudentassistance.presentation.common.BaseFragment
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.*

class LoginFragment : BaseFragment<LoginViewModel>() {

    private var loginFragmentBinding: FragmentLoginBinding? = null
    private val binding
        get() = loginFragmentBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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

        binding.loginButton.setOnClickListener { login() }

        viewModel.secondNameVerification.observe(viewLifecycleOwner, ::showSecondNameFieldError)
        viewModel.recordBookVerification.observe(viewLifecycleOwner, ::showRecordBookNumberFieldError)
        viewModel.loginResult.observe(viewLifecycleOwner, ::updateUIState)
    }

    private fun login() {
        hideKeyboard()
        if (isConnected()) {
            disableAll()
            viewModel.loginStudent(
                binding.secondName.textToString(),
                binding.recordBookNumber.textToString()
            )
        } else {
            showError(R.string.connection_lost)
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }

    private fun showSecondNameFieldError(result: VerificationResult) {
        if (result == VerificationResult.FIELD_IS_TOO_SHORT) {
            binding.secondNameTextInputLayout.showError(getString(R.string.second_name_too_short))
        }
        if (result == VerificationResult.FIELD_IS_EMPTY) {
            binding.secondNameTextInputLayout.showError(getString(R.string.second_name_is_empty))
        }
    }

    private fun showRecordBookNumberFieldError(result: VerificationResult) {
        if (result == VerificationResult.FIELD_IS_TOO_SHORT) {
            binding.recordBookNumberTextInputLayout.showError(getString(R.string.record_book_too_short))
        }
        if (result == VerificationResult.FIELD_IS_EMPTY) {
            binding.recordBookNumberTextInputLayout.showError(getString(R.string.record_book_is_empty))
        }
    }

    private fun updateUIState(isStudentLogined: Boolean) {
        if (isStudentLogined) {
            hideAll()
        } else {
            enableAll()
        }
    }

    private fun hideAll() {
        binding.appName.gone()
        binding.secondNameTextInputLayout.gone()
        binding.recordBookNumberTextInputLayout.gone()
        binding.loginButton.gone()
    }

    private fun disableAll() {
        binding.secondNameTextInputLayout.disable()
        binding.recordBookNumberTextInputLayout.disable()
        binding.loginButton.disable()
    }

    private fun enableAll() {
        binding.secondNameTextInputLayout.enable()
        binding.recordBookNumberTextInputLayout.enable()
        binding.loginButton.enable()
    }

    override fun getViewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun performError(error: Error) {
        enableAll()
        showError(error.errorMessageResId)
        if (error.errorMessageResId == R.string.student_not_found) {
            binding.secondNameTextInputLayout.showError()
            binding.recordBookNumberTextInputLayout.showError()
        }
    }
}