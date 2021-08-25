package com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.snakelord.pets.kbsustudentassistance.data.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.databinding.ItemInstituteBinding

/**
 * ViewHolder для отображения информации о локации
 *
 * @property locationBinding экземпляр [ItemInstituteBinding] для работы с View
 * @property clickListener слушатель нажатий, принимающий в качестве параметров id кнопки и
 *           позицию элемента
 *
 * @author Murad Luguev on 26-08-2021
 */
class LocationViewHolder(
    private val locationBinding: ItemInstituteBinding,
    private val clickListener: (Int, Int) -> Unit
) : RecyclerView.ViewHolder(locationBinding.root) {

    /**
     * Метод для связывания данных из [LocationModel] с необходимыми View
     *
     * @param locationModel экземпляр [LocationModel]
     */
    fun bind(locationModel: LocationModel) {
        locationBinding.instituteName.text = itemView.context.getString(locationModel.enterRes)

        locationBinding.openOnMap.setOnClickListener {
            clickListener.invoke(locationBinding.openOnMap.id, adapterPosition)
        }

        locationBinding.makeAPath.setOnClickListener {
            clickListener.invoke(locationBinding.makeAPath.id, adapterPosition)
        }
    }
}