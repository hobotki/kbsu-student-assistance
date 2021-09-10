package com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.lectures_minimal.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.snakelord.pets.kbsustudentassistance.databinding.ItemLectureMinimalBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture

/**
 * ViewHolder для лекций
 *
 * @property itemLectureMinimalBinding ViewBinding для работы с View
 *
 * @author Murad Luguev on 01-09-2021
 */
class LecturesMinimalViewHolder(
    private val itemLectureMinimalBinding: ItemLectureMinimalBinding,
) : RecyclerView.ViewHolder(itemLectureMinimalBinding.root) {

    /**
     * Связывает поля модели лекции [Lecture] с View
     *
     * @param lecture модель лекции
     */
    fun bind(lecture: Lecture) {
        itemLectureMinimalBinding.lectureName.text = lecture.lectureName
        itemLectureMinimalBinding.teacherName.text = lecture.teacher
    }
}