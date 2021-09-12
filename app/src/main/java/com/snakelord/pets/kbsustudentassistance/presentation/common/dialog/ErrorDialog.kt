package com.snakelord.pets.kbsustudentassistance.presentation.common.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.DialogErrorBinding
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.expand
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.gone

/**
 * Диалог для отображения информации об ошибке
 *
 * @author Murad Luguev on 12-09-2021
 */
class ErrorDialog : BottomSheetDialogFragment() {

    private var errorDialogBinding: DialogErrorBinding? = null
    private val binding
        get() = errorDialogBinding!!

    private var onTryAction: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        errorDialogBinding = DialogErrorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
    }

    private fun initUI() {
        val args = requireArguments()
        val errorMessageId = args.getInt(ERROR_MESSAGE_KEY)

        binding.errorMessage.text = getString(errorMessageId)

        if (errorMessageId == R.string.requested_info_not_found)
            binding.positiveButton.gone()

        binding.positiveButton.setOnClickListener {
            onTryAction?.invoke()
            dismiss()
        }

        binding.neutralButton.setOnClickListener { dismiss() }
        expand()
    }

    private fun expand() {
        val viewGroup: ViewGroup = dialog!!.findViewById(
            com.google.android.material.R.id.design_bottom_sheet
        )
        val bottomSheetBehavior = BottomSheetBehavior.from(viewGroup)
        bottomSheetBehavior.expand()
    }

    private fun setOnTryAction(action: (() -> Unit)?) {
        onTryAction = action
    }

    override fun onPause() {
        super.onPause()

        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        errorDialogBinding = null
    }

    /**
     * Класс для создания экземпляра [ErrorDialog]
     *
     * @author Murad Luguev on 12-09-2021
     */
    class Builder {
        private val errorDialog = ErrorDialog()

        /**
         * Функция для добавления информации об ошибке
         *
         * @param errorMessageId идентификатор строкового ресурса для отображения информации об ошибке
         *
         * @return экземпляр сборщика типа [Builder]
         */
        fun errorMessage(@StringRes errorMessageId: Int): Builder {
            val args = Bundle(ARGS_BUNDLE_CAPACITY)
            args.putInt(ERROR_MESSAGE_KEY, errorMessageId)
            errorDialog.arguments = args
            return this
        }

        /**
         * Функция для добавления действия для повтора в случае возникновения ошибки
         *
         * @param action функция, которую необходимо выполнить по нажатию на кнопку "Повторить попытку"
         *
         * @return экземпляр сборщика типа [Builder]
         */
        fun onTryAction(action: (() -> Unit)?): Builder {
            errorDialog.setOnTryAction(action)
            return this
        }

        /**
         * Функция, для создания экземпляра диалога об ошибке типа [ErrorDialog]
         *
         * @return кземпляр диалога об ошибке типа [ErrorDialog]
         */
        fun create(): ErrorDialog {
            return errorDialog
        }

        companion object {
            private const val ARGS_BUNDLE_CAPACITY = 1
        }
    }

    companion object {
        private const val ERROR_MESSAGE_KEY = "error_message"
    }
}