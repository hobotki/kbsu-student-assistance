package com.snakelord.pets.kbsustudentassistance.presentation.common

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.snakelord.pets.kbsustudentassistance.domain.model.Error
import com.snakelord.pets.kbsustudentassistance.presentation.application.KbsuStudentAssistanceApp
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.moveToTop

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    private val factory = KbsuStudentAssistanceApp.applicationComponent.viewModelFactory()
    protected lateinit var viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[getViewModelClass()]
        viewModel.errors.observe(viewLifecycleOwner, ::performError)
    }

    abstract fun getViewModelClass(): Class<VM>

    open fun performError(error: Error) {}

    protected fun showError(@StringRes errorMessageResId: Int) {
        Snackbar.make(requireView(), errorMessageResId, Snackbar.LENGTH_LONG)
            .moveToTop()
            .show()
    }
}