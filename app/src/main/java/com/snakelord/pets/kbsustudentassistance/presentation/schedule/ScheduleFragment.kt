package com.snakelord.pets.kbsustudentassistance.presentation.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.DayDto
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.snakelord.pets.kbsustudentassistance.databinding.FragmentScheduleBinding
import com.snakelord.pets.kbsustudentassistance.databinding.ItemLectureBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.presentation.common.fragment.BaseFragment
import com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.LecturesAdapter

class ScheduleFragment : BaseFragment() {

    private val scheduleViewModel: ScheduleViewModel by navGraphViewModels(navGraphId) { factory }
    private lateinit var binding: FragmentScheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.schedule.layoutManager = LinearLayoutManager(requireContext())
        scheduleViewModel.schedule.observe(viewLifecycleOwner, ::showSchedule)
    }

    private fun showSchedule(schedule: List<Lecture>) {
        val lecturesAdapter = LecturesAdapter(schedule)
        binding.schedule.adapter = lecturesAdapter
    }
}