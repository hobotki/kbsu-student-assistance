package com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.lectures.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.ItemLectureBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture

/**
 * ViewHolder для отоюражения информации о лекции
 *
 * @property itemLectureBinding ViewBinding для работы с View
 * @property clickListener слушатель нажатий для передачи позиции элемента
 *
 * @author Murad Luguev on 01-09-2021
 */
class LecturesViewHolder(
    private val itemLectureBinding: ItemLectureBinding,
    private val clickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(itemLectureBinding.root) {

    /**
     * Связывает поля из модели [Lecture] с View
     *
     * @param lecture модель лекции [Lecture]
     */
    fun bind(lecture: Lecture) {
        itemLectureBinding.lectureTextView.text = lecture.lectureName
        itemLectureBinding.teacherNameTextView.text = lecture.teacher
        itemLectureBinding.startTimeTextView.text = lecture.startTime
        itemLectureBinding.finishTimeTextView.text = lecture.endTime

        itemLectureBinding.classroomTextView.text =
            itemView.context.getString(R.string.classroom_placeholder, lecture.classroom)
        itemLectureBinding.classroomTextView.setOnClickListener {
            clickListener.invoke(adapterPosition)
        }
    }
}
