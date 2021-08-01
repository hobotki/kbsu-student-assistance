package com.snakelord.pets.kbsustudentassistance.presentation.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.model.State
import com.snakelord.pets.kbsustudentassistance.data.model.Student
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentLoginBinding
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult
import com.snakelord.pets.kbsustudentassistance.presentation.common.BaseFragment
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.*

class LoginFragment : BaseFragment() {

    private var loginFragmentBinding: FragmentLoginBinding? = null
    private val binding
        get() = loginFragmentBinding!!

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        loginFragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        loginViewModel.secondNameVerification.observe(viewLifecycleOwner, ::showSecondNameFieldError)
        loginViewModel.recordBookVerification.observe(viewLifecycleOwner, ::showRecordBookNumberFieldError)
        loginViewModel.loginResult.observe(viewLifecycleOwner, ::showSuccess)
    }

    private fun initUI() {
        binding.secondName
            .addTextChangedListener(DisableErrorTextWatcher(binding.secondNameTextInputLayout))

        binding.recordBookNumber
            .addTextChangedListener(DisableErrorTextWatcher(binding.recordBookNumberTextInputLayout))

        binding.loginButton.setOnClickListener { login() }
    }

    private fun login() {
        hideKeyboard()
        if (isConnected) {
            loginViewModel.loginStudent(
                binding.secondName.textToString(),
                binding.recordBookNumber.textToString()
            )
        } else {
            Snackbar.make(requireView(), R.string.connection_lost, Snackbar.LENGTH_LONG)
                .moveToTop()
                .show()
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireView().windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
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

    private fun showSuccess(state: State<Student>) {
        when (state) {
            State.Loading -> disableAll()
            is State.Error -> {
                enableAll()
                performError(state.errorMessageId)
            }
            is State.Successful<Student> -> hideAll()
        }
    }

    private fun performError(errorResId: Int) {
        when (errorResId) {
            R.string.second_name_miss_match -> binding.secondNameTextInputLayout.showError(getString(errorResId))
            R.string.record_book_miss_match -> binding.recordBookNumberTextInputLayout.showError(getString(errorResId))
            R.string.something_went_wrong -> {
                Snackbar.make(requireView(), errorResId, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

}