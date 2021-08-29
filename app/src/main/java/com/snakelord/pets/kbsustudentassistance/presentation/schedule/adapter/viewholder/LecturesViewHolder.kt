package com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.ItemLectureBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture

class LecturesViewHolder(
    private val itemLectureBinding: ItemLectureBinding
) : RecyclerView.ViewHolder(itemLectureBinding.root) {

    fun bind(lectureDto: Lecture) {
        itemLectureBinding.lectureName.text = lectureDto.lectureName
        itemLectureBinding.teacherName.text = lectureDto.teacher
        itemLectureBinding.classroom.text =
            itemView.context.getString(R.string.classroom_placeholder, lectureDto.classroom)
        itemLectureBinding.startTime.text = lectureDto.startTime
        itemLectureBinding.endTime.text = lectureDto.endTime
    }
}