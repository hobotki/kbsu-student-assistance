package com.snakelord.pets.kbsustudentassistance.presentation.pass

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.navGraphViewModels
import com.google.android.material.snackbar.Snackbar
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentPassBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.gone
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.invisible
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.visible
import com.snakelord.pets.kbsustudentassistance.presentation.common.fragment.BaseFragment
import com.snakelord.pets.kbsustudentassistance.presentation.common.state.UIStates

/**
 * Фрагмент пропуска
 *
 * @author Murad Luguev on 11-09-2021
 */
class PassFragment : BaseFragment() {

    private var fragmentPassBinding: FragmentPassBinding? = null
    private val binding
        get() = fragmentPassBinding!!

    private val passViewModel: PassViewModel by navGraphViewModels(navGraphId) { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentPassBinding = FragmentPassBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        passViewModel.qrcodeBitmap.observe(viewLifecycleOwner, ::showQrCode)
        passViewModel.student.observe(viewLifecycleOwner, ::showStudentInfo)
        passViewModel.uiStates.observe(viewLifecycleOwner, ::updateUIState)
    }

    override fun onResume() {
        super.onResume()
        passViewModel.getQrCode(binding.studentQrCode.measuredWidth)
    }

    private fun showQrCode(qrCodeBitmap: Bitmap) {
        binding.studentQrCode.setImageBitmap(qrCodeBitmap)
    }

    private fun showStudentInfo(student: Student) {
        binding.studentFullName.text = student.fullName
        binding.year.text = requireContext().getString(
            R.string.year_placeholder, student.specialtyCode.last()
        )
    }

    override fun updateUIState(state: UIStates) {
        when(state) {
            is UIStates.Loading -> {
                binding.progressBar.visible()
                binding.studentFullName.gone()
                binding.year.gone()
                binding.guideMessage.gone()
                binding.studentQrCode.invisible()
            }
            is UIStates.Successful -> {
                binding.progressBar.gone()
                binding.studentFullName.visible()
                binding.year.visible()
                binding.guideMessage.visible()
                binding.studentQrCode.visible()
            }
            is UIStates.Error -> {
                showError(state.error.errorMessageResId)
            }
        }
    }
}