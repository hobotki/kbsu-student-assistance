package com.snakelord.pets.kbsustudentassistance.presentation.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.android.material.textfield.TextInputLayout
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentLoginBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.login.VerificationResult
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.navigationCallback
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.requireViewBinding
import com.snakelord.pets.kbsustudentassistance.presentation.common.fragment.BaseFragment
import com.snakelord.pets.kbsustudentassistance.presentation.login.extensions.showError
import com.snakelord.pets.kbsustudentassistance.presentation.login.extensions.fieldAsString
import com.snakelord.pets.kbsustudentassistance.presentation.login.textwatcher.ErrorDisablingTextWatcher

/**
 * Фрагмен авторизации
 *
 * @author Murad Luguev on 27-08-2021
 */
class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private var loginFragmentBinding: FragmentLoginBinding? = null
    private val binding
        get() = requireViewBinding(loginFragmentBinding)

    private val loginViewModel: LoginViewModel by navGraphViewModels(navGraphId) { factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginFragmentBinding = FragmentLoginBinding.bind(view)
        initUI()
    }

    private fun initUI() {
        updateElementsVisibility(visible = true)
        binding.loginButton.setOnClickListener { login() }

        loginViewModel.secondNameVerification.observe(viewLifecycleOwner) { result ->
            showFieldError(result, binding.secondNameTextInputLayout)
        }

        loginViewModel.recordBookVerification.observe(viewLifecycleOwner) { result ->
            showFieldError(result, binding.recordBookNumberTextInputLayout)
        }

        loginViewModel.uiStates.observe(viewLifecycleOwner, ::updateUIState)
    }

    private fun login() {
        hideKeyboard()
        loginViewModel.loginStudent(
            secondName = binding.secondNameTextInputEditText.fieldAsString(),
            recordBookNumber = binding.recordBookNumberTextInputEditText.fieldAsString()
        )
    }

    private fun hideKeyboard() {
        val inputMethodManager = requireContext()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        inputMethodManager.hideSoftInputFromWindow(
            requireView().windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN
        )
    }

    private fun showFieldError(result: VerificationResult, textInputLayout: TextInputLayout) {
        val messageResId = when (result) {
            VerificationResult.FIELD_IS_TOO_SHORT -> R.string.text_is_too_short

            VerificationResult.FIELD_IS_EMPTY -> R.string.field_is_empty

            VerificationResult.FIELD_CONTAINS_INVALID_SYMBOLS ->
                R.string.field_contains_invalid_character

            VerificationResult.SUCCESSFUL -> return
        }
        textInputLayout.showError(getString(messageResId))
        textInputLayout.editText?.addTextChangedListener(ErrorDisablingTextWatcher(textInputLayout))
    }

    private fun moveToMainFragment() {
        updateElementsVisibility(visible = false)
        findNavController().apply {
            navigate(R.id.go_to_schedule)
            navigationCallback.showNavigationBar(show = true)
        }
    }

    private fun updateElementsVisibility(visible: Boolean) {
        binding.appNameTextView.isVisible = visible
        binding.secondNameTextInputLayout.isVisible = visible
        binding.recordBookNumberTextInputLayout.isVisible = visible
        binding.loginButton.isVisible = visible
    }

    private fun updateElementsEnabledState(enabled: Boolean) {
        binding.secondNameTextInputLayout.isEnabled = enabled
        binding.recordBookNumberTextInputLayout.isEnabled = enabled
        binding.loginButton.isEnabled = enabled
    }

    override fun onLoading() = updateElementsEnabledState(enabled = false)

    override fun onSuccess() = moveToMainFragment()

    override fun showError(errorMessageResId: Int) {
        super.showError(errorMessageResId)

        updateElementsEnabledState(enabled = true)
    }

    override fun getOnTryAction(): (() -> Unit) = ::login

    override fun onDestroyView() {
        super.onDestroyView()

        loginFragmentBinding = null
    }
}
