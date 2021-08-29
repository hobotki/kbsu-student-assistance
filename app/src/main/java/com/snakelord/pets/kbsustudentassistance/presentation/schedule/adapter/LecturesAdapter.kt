package com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snakelord.pets.kbsustudentassistance.data.datasource.api.schedule.model.LectureDto
import com.snakelord.pets.kbsustudentassistance.databinding.ItemLectureBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.Lecture
import com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.viewholder.LecturesViewHolder

class LecturesAdapter(
    private val lectures: List<Lecture>
) : RecyclerView.Adapter<LecturesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LecturesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return LecturesViewHolder(
            ItemLectureBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LecturesViewHolder, position: Int) {
        holder.bind(lectures[position])
    }

    override fun getItemCount(): Int {
        return lectures.size
    }

}