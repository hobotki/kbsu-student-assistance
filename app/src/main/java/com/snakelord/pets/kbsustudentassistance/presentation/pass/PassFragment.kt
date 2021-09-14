package com.snakelord.pets.kbsustudentassistance.presentation.pass

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentPassBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.pass.Student
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.gone
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.invisible
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.visible
import com.snakelord.pets.kbsustudentassistance.presentation.common.fragment.BaseFragment

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

        setPortraitOrientation()
    }

    override fun onResume() {
        super.onResume()

        passViewModel.getQrCode(binding.studentQrCode.width)
    }

    private fun showQrCode(qrCodeBitmap: Bitmap) {
        binding.studentQrCode.setImageBitmap(qrCodeBitmap)
    }

    private fun showStudentInfo(student: Student) {
        binding.studentFullName.text = student.fullName
        binding.year.text = requireContext().getString(
            R.string.year_placeholder, student.specialityCode.last()
        )
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        passViewModel.getQrCode(binding.studentQrCode.measuredWidth)
    }
    override fun onLoading() {
        binding.progressBar.visible()
        binding.studentFullName.gone()
        binding.year.gone()
        binding.guideMessage.gone()
        binding.studentQrCode.invisible()
    }

    override fun onSuccess() {
        binding.progressBar.gone()
        binding.studentFullName.visible()
        binding.year.visible()
        binding.guideMessage.visible()
        binding.studentQrCode.visible()
    }
}