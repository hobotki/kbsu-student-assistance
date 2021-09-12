package com.snakelord.pets.kbsustudentassistance.presentation.common.fragment

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.presentation.application.KbsuStudentAssistanceApp
import com.snakelord.pets.kbsustudentassistance.presentation.common.dialog.ErrorDialog
import com.snakelord.pets.kbsustudentassistance.presentation.common.state.UIStates

/**
 * Базовый фрагмент для всех фрагментов в приложении
 * Содержит базовое поведение для отображения ошибок,
 * изменение сотояния экрана в зависимости от [UIStates],
 *
 * @author Murad Luguev on 27-08-2021
 */
abstract class BaseFragment : Fragment() {

    protected val factory = KbsuStudentAssistanceApp.applicationComponent
        .viewModelFactory()

    @IdRes
    protected val navGraphId = R.id.nav_graph

    private var errorDialog: ErrorDialog? = null
    /**
     * Функция для обновления экрана в зависимости от состояния [UIStates]
     *
     * @param state экземпляр sealed-класса [UIStates]
     */
    open fun updateUIState(state: UIStates) {
        //Переопределяется по необходимости
    }

    open fun setOnTryAction(): (() -> Unit)? {
        return null
    }

    /**
     * Функция для отображения ошибки
     *
     * Отображает [Snackbar] вверху экрана с сообщением об ошибке
     *
     * @param errorMessageResId строковый ресурс для отоюражения ошибки
     */
    protected fun showError(@StringRes errorMessageResId: Int) {
        errorDialog = ErrorDialog.Builder()
            .errorMessage(errorMessageResId)
            .onTryAction(setOnTryAction())
            .create()
        errorDialog!!.show(parentFragmentManager, ERROR_DIALOG_TAG)
    }

    companion object {
        private const val ERROR_DIALOG_TAG = "error_dialog"
    }
}