package com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.locations.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.snakelord.pets.kbsustudentassistance.presentation.common.extensions.viewContext
import com.snakelord.pets.kbsustudentassistance.domain.model.location.LocationModel
import com.snakelord.pets.kbsustudentassistance.databinding.ItemInstituteBinding
import com.snakelord.pets.kbsustudentassistance.presentation.navigation.adapter.lectures_minimal.LecturesMinimalAdapter

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
        locationBinding.instituteName.text = viewContext.getString(locationModel.enterRes)

        val linearLayoutManager = LinearLayoutManager(viewContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val lecturesMinimalAdapter = LecturesMinimalAdapter(locationModel.lectures)

        locationBinding.lectures.layoutManager = linearLayoutManager
        locationBinding.lectures.adapter = lecturesMinimalAdapter

        locationBinding.openOnMap.setOnClickListener {
            clickListener.invoke(locationBinding.openOnMap.id, adapterPosition)
        }

        locationBinding.makeAPath.setOnClickListener {
            clickListener.invoke(locationBinding.makeAPath.id, adapterPosition)
        }
    }
}