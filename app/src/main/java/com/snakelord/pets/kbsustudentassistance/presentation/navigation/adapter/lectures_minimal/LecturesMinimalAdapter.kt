package com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.lectures_minimal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snakelord.pets.kbsustudentassistance.databinding.ItemLectureMinimalBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.lectures_minimal.viewholder.LecturesMinimalViewHolder

/**
 * Адаптер для отображения минимальной информации о лекции
 *
 * @property lectures лекции
 *
 * @author Murad Luguev on 01-09-2021
 */
class LecturesMinimalAdapter(
    private val lectures: List<Lecture>
) : RecyclerView.Adapter<LecturesMinimalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LecturesMinimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LecturesMinimalViewHolder(
            ItemLectureMinimalBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LecturesMinimalViewHolder, position: Int) {
        holder.bind(lectures[position])
    }

    override fun getItemCount(): Int {
        return lectures.size
    }
}