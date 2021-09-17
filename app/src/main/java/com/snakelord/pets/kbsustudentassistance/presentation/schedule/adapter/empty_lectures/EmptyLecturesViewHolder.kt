package com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.empty_lectures

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.snakelord.pets.kbsustudentassistance.R

class EmptyLecturesViewHolder(
    itemView: View,
    clickListener: () -> Unit
) : RecyclerView.ViewHolder(itemView) {
    init {
        itemView.findViewById<Button>(R.id.tryAgain)
            .setOnClickListener { clickListener.invoke() }
    }
}