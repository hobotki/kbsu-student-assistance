package com.snakelord.pets.kbsustudentassistance.presentation.pass

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import androidx.navigation.navGraphViewModels
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentPassBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.gone
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.invisible
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.setPortraitOrientation
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.visible
import com.snakelord.pets.kbsustudentassistance.presentation.common.fragment.BaseFragment

/**
 * Фрагмент пропуска
 *
 * @author Murad Luguev on 11-09-2021
 */
class PassFragment : BaseFragment(R.layout.fragment_pass) {

    private var fragmentPassBinding: FragmentPassBinding? = null
    private val binding
        get() = requireBinding(fragmentPassBinding)

    private val passViewModel: PassViewModel by navGraphViewModels(navGraphId) { factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setPortraitOrientation()
        fragmentPassBinding = FragmentPassBinding.bind(view)
        passViewModel.qrcodeBitmap.observe(viewLifecycleOwner, ::showQrCode)
        passViewModel.student.observe(viewLifecycleOwner, ::showStudentInfo)
        passViewModel.uiStates.observe(viewLifecycleOwner, ::updateUIState)

        passViewModel.getQrCode(resources.getDimensionPixelSize(R.dimen.qr_code_image_view_size))
    }

    private fun showQrCode(qrCodeBitmap: Bitmap) {
        binding.qrCodeImageView.setImageBitmap(qrCodeBitmap)
    }

    private fun showStudentInfo(student: Student) {
        binding.studentFullNameTextView.text = student.fullName
        binding.studentYearTextView.text = requireContext().getString(
            R.string.year_placeholder, student.specialityCode.last()
        )
    }

    override fun onLoading() {
        binding.progressBarCircular.visible()
        binding.studentFullNameTextView.gone()
        binding.studentYearTextView.gone()
        binding.guideTextView.gone()
        binding.qrCodeImageView.invisible()
    }

    override fun onSuccess() {
        binding.progressBarCircular.gone()
        binding.studentFullNameTextView.visible()
        binding.studentYearTextView.visible()
        binding.guideTextView.visible()
        binding.qrCodeImageView.visible()
    }
}
