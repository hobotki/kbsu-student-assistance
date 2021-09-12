package com.snakelord.pets.kbsustudentassistance.presentation.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentLoginBinding
import com.snakelord.pets.kbsustudentassistance.domain.VerificationResult
import com.snakelord.pets.kbsustudentassistance.presentation.common.fragment.BaseFragment
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.*
import com.snakelord.pets.kbsustudentassistance.presentation.common.state.UIStates

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        loginViewModel.secondNameVerification
            .observe(viewLifecycleOwner, ::showSecondNameFieldError)

        loginViewModel.recordBookVerification
            .observe(viewLifecycleOwner, ::showRecordBookNumberFieldError)

        loginViewModel.uiStates.observe(viewLifecycleOwner, ::updateUIState)
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
    }

    private fun showRecordBookNumberFieldError(result: VerificationResult) {
        if (result == VerificationResult.FIELD_IS_TOO_SHORT) {
            binding.recordBookNumberTextInputLayout.showError(getString(R.string.record_book_too_short))
        }
        if (result == VerificationResult.FIELD_IS_EMPTY) {
            binding.recordBookNumberTextInputLayout.showError(getString(R.string.record_book_is_empty))
        }
    }

    private fun moveToMainFragment() {
        hideAll()
        findNavController().apply {
            navigate(R.id.action_loginFragment_to_scheduleFragment)
            navigationCallback.showNavigationView()
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

    override fun updateUIState(state: UIStates) {
        when(state) {
            is UIStates.Loading -> {
                disableAll()
            }
            is UIStates.Successful -> {
                enableAll()
                moveToMainFragment()
            }
            is UIStates.Error -> {
                enableAll()
                showError(state.error.errorMessageResId)
                if (state.error.errorMessageResId == R.string.requested_info_not_found) {
                    binding.secondNameTextInputLayout.showError()
                    binding.recordBookNumberTextInputLayout.showError()
                }
            }
        }
    }

    override fun setOnTryAction(): (() -> Unit) {
        return ::login
    }
}