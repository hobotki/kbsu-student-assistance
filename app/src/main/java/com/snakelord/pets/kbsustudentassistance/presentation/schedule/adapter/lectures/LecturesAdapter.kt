package com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.lectures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snakelord.pets.kbsustudentassistance.databinding.ItemLectureBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.lectures.viewholder.LecturesViewHolder

/**
 * Адаптер для лекций
 *
 * @property lectures список лекций
 * @property clickListener слушатель нажатий для отображения локации,
 * в которой будет проходить лекция
 *
 * @author Murad Luguev on 01-09-2021
 */
class LecturesAdapter(
    private val lectures: List<Lecture>,
    private val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<LecturesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LecturesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return LecturesViewHolder(
            ItemLectureBinding.inflate(layoutInflater, parent, false),
            ::showLocationById
        )
    }

    override fun onBindViewHolder(holder: LecturesViewHolder, position: Int) {
        holder.bind(lectures[position])
    }

    private fun showLocationById(itemPosition: Int) {
        clickListener.invoke(
            lectures[itemPosition].instituteId
        )
    }

    override fun getItemCount(): Int {
        return lectures.size
    }

}