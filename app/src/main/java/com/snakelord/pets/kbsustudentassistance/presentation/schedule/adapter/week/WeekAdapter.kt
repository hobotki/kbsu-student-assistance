package com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.week

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snakelord.pets.kbsustudentassistance.databinding.ItemDayBinding
import com.snakelord.pets.kbsustudentassistance.domain.model.schedule.DayOfWeek
import com.snakelord.pets.kbsustudentassistance.presentation.schedule.adapter.week.viewholder.DayViewHolder

/**
 * Адаптер для отображения дней недели
 *
 * @property days дни недели
 * @property clickListener слушатель нажатий для отображения расписания исходя из выбранного дня
 * @property selectedItem выбранный день (необходимо для сохранения состояния)
 *
 * @author Murad Luguev on 01-09-2021
 */
class WeekAdapter(
    private val days: List<DayOfWeek>,
    private val clickListener: (Int) -> Unit,
    private var selectedItem: Int,
) : RecyclerView.Adapter<DayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return DayViewHolder(
            ItemDayBinding.inflate(inflater, parent, false),
            ::updateSelectedState
        )
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(days[position])
        holder.updateSelectState(selectedItem)
    }

    private fun updateSelectedState(newPosition: Int) {
        notifyItemChanged(selectedItem)
        selectedItem = newPosition
        notifyItemChanged(selectedItem)
        clickListener.invoke(newPosition)
    }

    override fun getItemCount(): Int {
        return days.size
    }
}