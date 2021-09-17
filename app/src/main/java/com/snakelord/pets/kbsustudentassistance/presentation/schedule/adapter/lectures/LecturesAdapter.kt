package com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.lectures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snakelord.pets.kbsustudentassistance.R
import com.snakelord.pets.kbsustudentassistance.databinding.ItemLectureBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.empty_lectures.EmptyLecturesViewHolder
import com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.lectures.viewholder.LecturesViewHolder

/**
 * Адаптер для лекций
 *
 * @property lectures список лекций
 * @property classRoomClickListener слушатель нажатий для отображения локации,
 * в которой будет проходить лекция
 * @property tryAgainAction функция для повторной попытки загрузить расписание
 *
 * @author Murad Luguev on 01-09-2021
 */
class LecturesAdapter(
    private val lectures: List<Lecture>,
    private val classRoomClickListener: (Int) -> Unit,
    private val tryAgainAction: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (lectures.isEmpty()) {
            return EMPTY_LECTURES_VIEW_TYPE
        }
        return LECTURE_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return if (viewType == LECTURE_VIEW_TYPE) {
            LecturesViewHolder(
                ItemLectureBinding.inflate(layoutInflater, parent, false),
                ::showLocationById
            )
        } else {
            EmptyLecturesViewHolder(
                layoutInflater.inflate(R.layout.item_empty_lectutes, parent, false),
                ::tryAgain
            )
        }
    }

    private fun showLocationById(itemPosition: Int) {
        classRoomClickListener.invoke(
            lectures[itemPosition].instituteId
        )
    }

    private fun tryAgain() {
        tryAgainAction.invoke()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LecturesViewHolder) {
            holder.bind(lectures[position])
        }
    }

    override fun getItemCount(): Int {
        return if (lectures.isNotEmpty()) {
            lectures.size
        } else {
            EMPTY_LECTURES_SIZE
        }
    }

    companion object {
        private const val LECTURE_VIEW_TYPE = 0
        private const val EMPTY_LECTURES_VIEW_TYPE = -1
        private const val EMPTY_LECTURES_SIZE = 1
    }
}