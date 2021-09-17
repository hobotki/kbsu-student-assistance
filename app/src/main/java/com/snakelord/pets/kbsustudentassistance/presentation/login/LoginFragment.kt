package com.snakelord.pets.kbsustudentassistance.presentation.login

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
class LoginFragment : BaseFragment() {

    private var loginFragmentBinding: FragmentLoginBinding? = null
    private val binding
        get() = loginFragmentBinding!!

    private val loginViewModel: LoginViewModel by navGraphViewModels(navGraphId) { factory }

    private var secondNameTextWatcher: TextWatcher? = null
    private var recordBookNumberTextWatcher: TextWatcher? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        loginFragmentBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        showAll()

        initTextWatchers()

        binding.secondName
            .addTextChangedListener(secondNameTextWatcher)

        binding.recordBookNumber
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
            binding.secondName.textToString(),
            binding.recordBookNumber.textToString()
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
        hideAll()
        findNavController().apply {
            navigate(R.id.go_to_schedule)
            navigationCallback.showNavigationView()
        }
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

    override fun onLoading() {
        disableAll()
    }

    override fun onSuccess() {
        enableAll()
        moveToMainFragment()
    }

    override fun showError(errorMessageResId: Int) {
        super.showError(errorMessageResId)

        if (errorMessageResId == R.string.requested_info_not_found) {
            binding.secondNameTextInputLayout.showError()
            binding.recordBookNumberTextInputLayout.showError()
        }

        enableAll()
    }

    override fun getOnTryAction(): (() -> Unit) {
        return ::login
    }

    override fun onStop() {
        super.onStop()

        binding.secondName.removeTextChangedListener(secondNameTextWatcher)
        binding.recordBookNumber.removeTextChangedListener(recordBookNumberTextWatcher)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        loginFragmentBinding = null
    }
}