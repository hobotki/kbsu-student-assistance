package com.snakelord.pets.kbsustudentassistance.presentation.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentScheduleBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.gone
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.visible
import com.snakelord.pets.kbsustudentassistance.presentation.common.fragment.BaseFragment
import com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.lectures.LecturesAdapter
import com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.week.WeekAdapter
import com.snakelord.pets.kbsustudentassistance.presentation.schedule.extensions.getWeek
import java.util.*

/**
 * Фрагемент расписания
 *
 * @author Murad Luguev on 01-09-2021
 */
class ScheduleFragment : BaseFragment() {

    private val scheduleViewModel: ScheduleViewModel by navGraphViewModels(navGraphId) { factory }

    private var fragmentScheduleBinding: FragmentScheduleBinding? = null
    private val binding
        get() = fragmentScheduleBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentScheduleBinding = FragmentScheduleBinding.inflate(
            inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWeekRecyclerView()
        binding.schedule.layoutManager = LinearLayoutManager(requireContext())
        scheduleViewModel.selectedSchedule.observe(viewLifecycleOwner, ::showSchedule)
        scheduleViewModel.uiStates.observe(viewLifecycleOwner, ::updateUIState)
    }

    private fun initWeekRecyclerView() {
        val calendar = Calendar.getInstance(Locale.forLanguageTag(RU_LANGUAGE_TAG))
        val today = scheduleViewModel.selectedIndex
        val week = calendar.getWeek()
        val weekAdapter = WeekAdapter(
            week,
            scheduleViewModel::showScheduleByDay,
            today
        )
        binding.week.adapter = weekAdapter
    }

    override fun onLoading() {
        binding.progressBar.visible()
        binding.schedule.gone()
        binding.appBarLayout.gone()
    }

    override fun onSuccess() {
        binding.progressBar.gone()
        binding.schedule.visible()
        binding.appBarLayout.visible()
    }

    override fun showError(errorMessageResId: Int) {
        super.showError(errorMessageResId)

        binding.progressBar.gone()
        binding.schedule.visible()
        binding.appBarLayout.gone()
        showSchedule(emptyList())
    }

    private fun showSchedule(schedule: List<Lecture>) {
        val lecturesAdapter = LecturesAdapter(schedule, ::showLocationById, getOnTryAction())
        binding.schedule.adapter = lecturesAdapter
    }

    private fun showLocationById(instituteId: Int) {
        val args = Bundle(ARGS_CAPACITY)
        args.putInt(ARGUMENT_INSTITUTE_ID, instituteId)

        findNavController()
            .navigate(
                R.id.action_scheduleFragment_to_navigationFragment,
                args
            )
    }

    override fun onDestroy() {
        super.onDestroy()

        fragmentScheduleBinding = null
    }

    override fun getOnTryAction(): () -> Unit {
        return scheduleViewModel::loadScheduleFromApi
    }

    companion object {
        private const val RU_LANGUAGE_TAG = "ru-RU"
        private const val ARGUMENT_INSTITUTE_ID = "instituteId"
        private const val ARGS_CAPACITY = 1
    }
}