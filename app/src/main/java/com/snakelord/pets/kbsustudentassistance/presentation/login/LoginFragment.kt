package com.snakelord.pets.kbsustudentassistance.presentation.login

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.android.material.textfield.TextInputLayout
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentLoginBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.login.VerificationResult
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.*
import com.snakelord.pets.kbsustudentassistance.presentation.common.fragment.BaseFragment
import com.snakelord.pets.kbsustudentassistance.presentation.common.simple_interfaces.SimpleTextWatcher

/**
 * Фрагмен авторизации
 *
 * @author Murad Luguev on 27-08-2021
 */
class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private var loginFragmentBinding: FragmentLoginBinding? = null
    private val binding
        get() = requireBinding(loginFragmentBinding)

    private val loginViewModel: LoginViewModel by navGraphViewModels(navGraphId) { factory }

    private var secondNameTextWatcher: TextWatcher? = null
    private var recordBookNumberTextWatcher: TextWatcher? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginFragmentBinding = FragmentLoginBinding.bind(view)
        initUI()
    }

    private fun initUI() {
        updateElementsVisibility(isVisible = true)

        initTextWatchers()

        binding.secondNameTextInputEditText
            .addTextChangedListener(secondNameTextWatcher)

        binding.recordBookNumberTextInputEditText
            .addTextChangedListener(recordBookNumberTextWatcher)

        binding.loginButton.setOnClickListener { login() }

        loginViewModel.secondNameVerification
            .observe(viewLifecycleOwner, ::showSecondNameFieldError)

        loginViewModel.recordBookVerification
            .observe(viewLifecycleOwner, ::showRecordBookNumberFieldError)

        loginViewModel.uiStates.observe(viewLifecycleOwner, ::updateUIState)
    }

    private fun initTextWatchers() {
        secondNameTextWatcher = createTextWatcher(binding.secondNameTextInputLayout)

        recordBookNumberTextWatcher = createTextWatcher(binding.recordBookNumberTextInputLayout)
    }

    private fun createTextWatcher(textInputLayout: TextInputLayout): TextWatcher {
        return object : SimpleTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textInputLayout.isErrorEnabled = false
            }
        }
    }

    private fun login() {
        hideKeyboard()
        loginViewModel.loginStudent(
            binding.secondNameTextInputEditText.textToString(),
            binding.recordBookNumberTextInputEditText.textToString()
        )
    }

    private fun hideKeyboard() {
        val inputMethodManager = requireContext()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        inputMethodManager.hideSoftInputFromWindow(
            requireView().windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }

    private fun showSecondNameFieldError(result: VerificationResult) {
        if (result == VerificationResult.FIELD_IS_TOO_SHORT) {
            binding.secondNameTextInputLayout.showError(getString(R.string.second_name_too_short))
        }
        if (result == VerificationResult.FIELD_IS_EMPTY) {
            binding.secondNameTextInputLayout.showError(getString(R.string.second_name_is_empty))
        }
        if (result == VerificationResult.FIELD_CONTAINS_INVALID_SYMBOLS) {
            binding.secondNameTextInputLayout.showError(getString(
                R.string.field_contains_invalid_character)
            )
        }
    }

    private fun showRecordBookNumberFieldError(result: VerificationResult) {
        if (result == VerificationResult.FIELD_IS_TOO_SHORT) {
            binding.recordBookNumberTextInputLayout.showError(getString(
                R.string.record_book_too_short)
            )
        }
        if (result == VerificationResult.FIELD_IS_EMPTY) {
            binding.recordBookNumberTextInputLayout.showError(getString(
                R.string.record_book_is_empty)
            )
        }
        if (result == VerificationResult.FIELD_CONTAINS_INVALID_SYMBOLS) {
            binding.recordBookNumberTextInputLayout.showError(getString(
                R.string.field_contains_invalid_character)
            )
        }
    }

    private fun moveToMainFragment() {
        updateElementsVisibility(isVisible = false)
        findNavController().apply {
            navigate(R.id.go_to_schedule)
            navigationCallback.showNavigationView()
        }
    }

    private fun updateElementsVisibility(isVisible: Boolean) {
        binding.appNameTextView.isVisible = isVisible
        binding.secondNameTextInputLayout.isVisible = isVisible
        binding.recordBookNumberTextInputLayout.isVisible = isVisible
        binding.loginButton.isVisible = isVisible
    }

    private fun updateElementsEnabledState(isEnabled: Boolean) {
        binding.secondNameTextInputLayout.isEnabled = isEnabled
        binding.recordBookNumberTextInputLayout.isEnabled = isEnabled
        binding.loginButton.isEnabled = isEnabled
    }

    override fun onLoading() {
        updateElementsEnabledState(isEnabled = false)
    }

    override fun onSuccess() {
        updateElementsEnabledState(isEnabled = true)
        moveToMainFragment()
    }

    override fun showError(errorMessageResId: Int) {
        super.showError(errorMessageResId)

        if (errorMessageResId == R.string.requested_info_not_found) {
            binding.secondNameTextInputLayout.showError()
            binding.recordBookNumberTextInputLayout.showError()
        }

        updateElementsEnabledState(isEnabled = true)
    }

    override fun getOnTryAction(): (() -> Unit) {
        return ::login
    }

    override fun onStop() {
        super.onStop()

        binding.secondNameTextInputEditText.removeTextChangedListener(secondNameTextWatcher)
        binding.recordBookNumberTextInputEditText.removeTextChangedListener(
            recordBookNumberTextWatcher
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()

        loginFragmentBinding = null
    }
}
