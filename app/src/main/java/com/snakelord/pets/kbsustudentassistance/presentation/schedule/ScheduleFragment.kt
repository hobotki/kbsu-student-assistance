package com.snakelord.pets.kbsustudentassistance.presentation.schedule

import android.os.Bundle
import android.view.View
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
class ScheduleFragment : BaseFragment(R.layout.fragment_schedule) {

    private val scheduleViewModel: ScheduleViewModel by navGraphViewModels(navGraphId) { factory }

    private var fragmentScheduleBinding: FragmentScheduleBinding? = null
    private val binding
        get() = requireBinding(fragmentScheduleBinding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentScheduleBinding = FragmentScheduleBinding.bind(view)
        initWeekRecyclerView()
        binding.scheduleRecyclerView.layoutManager = LinearLayoutManager(requireContext())
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
        binding.weekRecyclerView.adapter = weekAdapter
    }

    override fun onLoading() {
        binding.progressBarCircular.visible()
        binding.scheduleRecyclerView.gone()
        binding.appBarLayout.gone()
    }

    override fun onSuccess() {
        binding.progressBarCircular.gone()
        binding.scheduleRecyclerView.visible()
        binding.appBarLayout.visible()
    }

    override fun showError(errorMessageResId: Int) {
        super.showError(errorMessageResId)

        binding.progressBarCircular.gone()
        binding.scheduleRecyclerView.visible()
        binding.appBarLayout.gone()
        showSchedule(emptyList())
    }

    private fun showSchedule(schedule: List<Lecture>) {
        val lecturesAdapter = LecturesAdapter(schedule, ::showLocationById, getOnTryAction())
        binding.scheduleRecyclerView.adapter = lecturesAdapter
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
